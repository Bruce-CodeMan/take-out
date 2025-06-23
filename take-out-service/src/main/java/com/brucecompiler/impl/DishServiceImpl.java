package com.brucecompiler.impl;

import com.brucecompiler.DishService;
import com.brucecompiler.constant.MessageConstant;
import com.brucecompiler.constant.StatusCodeConstant;
import com.brucecompiler.constant.StatusConstant;
import com.brucecompiler.dto.DishDTO;
import com.brucecompiler.dto.DishPageQueryDTO;
import com.brucecompiler.entity.Dish;
import com.brucecompiler.entity.DishFlavor;
import com.brucecompiler.entity.SetMeal;
import com.brucecompiler.exception.DeletionNotAllowedException;
import com.brucecompiler.mapper.DishFlavorMapper;
import com.brucecompiler.mapper.DishMapper;
import com.brucecompiler.mapper.SetMealDishMapper;
import com.brucecompiler.mapper.SetMealMapper;
import com.brucecompiler.result.PageResult;
import com.brucecompiler.vo.DishVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class DishServiceImpl implements DishService {

    private final DishMapper dishMapper;
    private final DishFlavorMapper dishFlavorMapper;
    private final SetMealDishMapper setMealDishMapper;
    private final SetMealMapper setMealMapper;

    @Autowired
    public DishServiceImpl(
            DishMapper dishMapper,
            DishFlavorMapper dishFlavorMapper,
            SetMealDishMapper setMealDishMapper,
            SetMealMapper setMealMapper) {
        this.dishMapper = dishMapper;
        this.dishFlavorMapper = dishFlavorMapper;
        this.setMealDishMapper = setMealDishMapper;
        this.setMealMapper = setMealMapper;
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

    @Override
    @Transactional
    public void deleteDish(List<Long> ids) {
        // 1. 删除菜品之前, 需要判断菜品是否启售, 启售中不允许删除
        ids.forEach(id -> {
            Dish dish = dishMapper.selectById(id);
            if(Objects.equals(dish.getStatus(), StatusConstant.ENABLED)){
                throw new DeletionNotAllowedException(StatusCodeConstant.FAILURE, MessageConstant.DISH_ON_SALE);
            }
        });

        // 2.需要判断菜品是否被套餐关联,关联了也不允许删除
        Integer count = setMealDishMapper.countByDishId(ids);
        if(count > 0) {
            throw new DeletionNotAllowedException(
                    StatusCodeConstant.FAILURE,
                    MessageConstant.DISH_BE_RELATED_BY_SET_MEAL
            );
        }

        // 3. 删除菜品基本信息, dish表
        dishMapper.deleteBatch(ids);

        // 4. 删除菜品口味列表信息
        dishFlavorMapper.deleteBatch(ids);
    }

    @Override
    @Transactional
    public void startOrStop(Integer status, Long id) {
        Dish dish = Dish.builder()
                .id(id)
                .status(status)
                .build();
        dishMapper.update(dish);

        if(status.equals(StatusConstant.DISABLED)) {
            List<SetMeal> setMealList = setMealMapper.getByDishId(id);
            for(SetMeal setMeal : setMealList) {
                if(setMeal != null) {
                    setMeal.setStatus(StatusConstant.DISABLED);
                    setMealMapper.update(setMeal);
                }
            }
        }
    }

    @Override
    public DishVO getById(Long id) {
         DishVO dishVO = new DishVO();

         // 1. 根据菜品id查询菜品基本信息, 封装到dishVO中
        Dish dish = dishMapper.selectById(id);
        BeanUtils.copyProperties(dish, dishVO);

        // 2.根据菜品id查询口味列表数据, 封装到dishVO中
        List<DishFlavor> flavors = dishFlavorMapper.selectByDishId(id);
        dishVO.setFlavors(flavors);

        return dishVO;
    }

    @Override
    @Transactional      // 0. 开启事务
    public void update(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        // 1. 修改菜品的基本信息, dish表
        dishMapper.update(dish);

        // 2. 修改口味列表信息, dish_flavor表
        dishFlavorMapper.deleteByDishId(dish.getId());

        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && !flavors.isEmpty()) {
            flavors.forEach(flavor -> {
                flavor.setDishId(dish.getId());
            });
            dishFlavorMapper.insertBatch(flavors);
        }
    }
}
