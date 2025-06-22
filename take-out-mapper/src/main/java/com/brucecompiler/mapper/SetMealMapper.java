package com.brucecompiler.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SetMealMapper {

    /**
     * Retrieves the count of the set meal based on the specified category id
     *
     * @param categoryId the category id
     * @return the count of the set meal
     */
    Integer countByCategoryId(Long categoryId);
}
