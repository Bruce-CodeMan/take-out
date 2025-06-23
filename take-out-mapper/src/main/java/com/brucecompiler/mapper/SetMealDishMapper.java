package com.brucecompiler.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetMealDishMapper {

    Integer countByDishId(List<Long> dishIds);
}
