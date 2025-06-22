package com.brucecompiler.impl;

import com.brucecompiler.DishService;
import com.brucecompiler.dto.DishDTO;
import com.brucecompiler.dto.DishPageQueryDTO;
import com.brucecompiler.entity.Dish;
import com.brucecompiler.entity.DishFlavor;
import com.brucecompiler.mapper.DishFlavorMapper;
import com.brucecompiler.mapper.DishMapper;
import com.brucecompiler.result.PageResult;
import com.brucecompiler.vo.DishVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private final DishMapper dishMapper;
    private final DishFlavorMapper dishFlavorMapper;

    @Autowired
    public DishServiceImpl(DishMapper dishMapper, DishFlavorMapper dishFlavorMapper) {
        this.dishMapper = dishMapper;
        this.dishFlavorMapper = dishFlavorMapper;
    }

    @Override
    @Transactional      // 0. 开始事务(涉及到多张表的DML操作需要开始事务)
    public void addDish(DishDTO dishDTO) {
        // 1. 构造菜品基本信息数据, 将其存入dish表中
        Dish dish = new Dish();
        // 2. 拷贝属性值
        BeanUtils.copyProperties(dishDTO, dish);
        // 3. 调用mapper方法保存
        dishMapper.insert(dish);

        // 4. 构造菜品口味列表数据
        List<DishFlavor> dishFlavorList = dishDTO.getFlavors();
        if(dishFlavorList != null && !dishFlavorList.isEmpty()) {
            dishFlavorList.forEach(dishFlavor -> {
                dishFlavor.setDishId(dish.getId());
            });

            dishFlavorMapper.insertBatch(dishFlavorList);
        }
    }

    @Override
    public PageResult<DishVO> page(DishPageQueryDTO dishPageQueryDTO) {

        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());

        Page<DishVO> page = dishMapper.list(dishPageQueryDTO);

        return new PageResult<>(page.getTotal(), page.getResult());
    }
}
