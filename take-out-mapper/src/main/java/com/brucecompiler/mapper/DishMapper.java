package com.brucecompiler.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper {

    /**
     * Retrieves the count based on the specified category id
     *
     * @param categoryId the category id from the category entity
     * @return the count of the dish
     */
    Integer countByCategoryId(Long categoryId);
}
