package com.brucecompiler.impl;

import java.util.List;

import com.brucecompiler.constant.MessageConstant;
import com.brucecompiler.constant.StatusCodeConstant;
import com.brucecompiler.entity.Dish;
import com.brucecompiler.exception.DeletionNotAllowedException;
import com.brucecompiler.exception.SetMealEnableException;
import com.brucecompiler.mapper.DishMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.aspectj.bridge.Message;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brucecompiler.SetMealService;
import com.brucecompiler.constant.StatusConstant;
import com.brucecompiler.dto.SetMealDTO;
import com.brucecompiler.dto.SetMealPageQueryDTO;
import com.brucecompiler.entity.SetMeal;
import com.brucecompiler.entity.SetMealDish;
import com.brucecompiler.mapper.SetMealDishMapper;
import com.brucecompiler.mapper.SetMealMapper;
import com.brucecompiler.result.PageResult;
import com.brucecompiler.vo.SetMealVO;

@Service
public class SetMealServiceImpl implements SetMealService {

    private final SetMealMapper setMealMapper;
    private final SetMealDishMapper setMealDishMapper;
    private final DishMapper dishMapper;

    @Autowired
    public SetMealServiceImpl(SetMealMapper setMealMapper, SetMealDishMapper setMealDishMapper, DishMapper dishMapper) {
        this.setMealMapper = setMealMapper;
        this.setMealDishMapper = setMealDishMapper;
        this.dishMapper = dishMapper;
    }

    @Override
    @Transactional
    public void save(SetMealDTO setMealDTO) {
        SetMeal setMeal = new SetMeal();
        BeanUtils.copyProperties(setMealDTO, setMeal);
        setMeal.setStatus(StatusConstant.DISABLED);
        setMealMapper.insert(setMeal);

        List<SetMealDish> setMealDishList = setMealDTO.getSetmealDishes();
        setMealDishList.forEach(setMealDish -> {
            setMealDish.setSetmealId(setMeal.getId());
        });
        setMealDishMapper.insertBatch(setMealDishList);
    }

    @Override
    public PageResult<SetMealVO> page(SetMealPageQueryDTO setMealPageQueryDTO) {
        int page = setMealPageQueryDTO.getPage();
        int pageSize = setMealPageQueryDTO.getPageSize();

        PageHelper.startPage(page, pageSize);
        Page<SetMealVO> setMealList = setMealMapper.pageQuery(setMealPageQueryDTO);
        return new PageResult<>(setMealList.getTotal(), setMealList.getResult());
    }

    @Override
    public SetMealVO getByIdWithDish(Long id) {
        return setMealMapper.getByIdWithDish(id);
    }

    @Override
    @Transactional
    public void update(SetMealDTO setMealDTO) {
        SetMeal setMeal = new SetMeal();
        BeanUtils.copyProperties(setMealDTO, setMeal);

        // 1. 修改套餐, 执行update
        setMealMapper.update(setMeal);

        // 2. 套餐id
        Long setMealId = setMealDTO.getId();

        // 3. 删除套餐和菜品的关联关系, 操作setmeal_dish表, 执行delete
        setMealDishMapper.deleteBySetMealId(setMealId);

        // 4. 重新批量删除
        List<SetMealDish> setMealDishList = setMealDTO.getSetmealDishes();
        setMealDishList.forEach(setMealDish -> {
            setMealDish.setSetmealId(setMealId);
        });
        setMealDishMapper.insertBatch(setMealDishList);
    }

    @Override
    public void startOrStop(Integer status, Long id) {
        // 启售的时候,判断套餐内是否有停售的菜品, 有停售菜品提示
        if(status.equals(StatusConstant.ENABLED)) {
            List<Dish> dishList = dishMapper.getBySetMealId(id);
            if(!dishList.isEmpty()) {
                dishList.forEach(dish -> {
                    if(StatusConstant.DISABLED.equals(dish.getStatus())) {
                        throw new SetMealEnableException(
                                StatusCodeConstant.FAILURE,
                                MessageConstant.SET_MEAL_ENABLED_FAILED
                        );
                    }
                });
            }
        }

        SetMeal setMeal = SetMeal.builder()
                .id(id)
                .status(status)
                .build();
        setMealMapper.update(setMeal);
    }

    @Override
    public void delete(List<Long> ids) {
        // 1. 启售中的套餐不能删除
        ids.forEach(id -> {
            SetMeal setMeal = setMealMapper.getById(id);
            if(setMeal.getStatus().equals(StatusConstant.ENABLED)) {
                throw new DeletionNotAllowedException(
                        StatusCodeConstant.FAILURE,
                        MessageConstant.SET_MEAL_ON_SALE
                );
            }
        });

        // 2. 删除套餐的同时, 需要将套餐菜品关系数据删除
        ids.forEach(setmealId -> {
            setMealMapper.delete(setmealId);
            setMealDishMapper.deleteBySetMealId(setmealId);
        });
    }
}
