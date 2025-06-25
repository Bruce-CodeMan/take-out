package com.brucecompiler.impl;

import com.brucecompiler.SetMealService;
import com.brucecompiler.constant.StatusConstant;
import com.brucecompiler.dto.SetMealDTO;
import com.brucecompiler.entity.SetMeal;
import com.brucecompiler.entity.SetMealDish;
import com.brucecompiler.mapper.SetMealDishMapper;
import com.brucecompiler.mapper.SetMealMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SetMealServiceImpl implements SetMealService {

    private final SetMealMapper setMealMapper;
    private final SetMealDishMapper setMealDishMapper;

    @Autowired
    public SetMealServiceImpl(SetMealMapper setMealMapper, SetMealDishMapper setMealDishMapper) {
        this.setMealMapper = setMealMapper;
        this.setMealDishMapper = setMealDishMapper;
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
}
