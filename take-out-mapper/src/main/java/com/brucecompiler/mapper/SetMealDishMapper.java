package com.brucecompiler.mapper;

import com.brucecompiler.entity.SetMealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetMealDishMapper {

    /**
     * Retrieves the count of records filtered by a list of dish IDs
     *
     * @param dishIds A list of dish IDs to filter the records. Each ID represents a unique dish
     * @return the count of records which match the given dish IDs
     */
    Integer countByDishId(List<Long> dishIds);

    /**
     * Inserts a batch of SetMealDish records into the database.
     *
     * @param setMealDishList A list of SetMealDish objects to be inserted.
     *                        Each object represents a relationship between a set meal and its associated dish.
     *                        The list must not be null or empty
     */
    void insertBatch(List<SetMealDish> setMealDishList);

    /**
     * Delete a set meal dish based on the set meal id
     *
     * @param id The unique id of the set meal
     */
    void deleteBySetMealId(Long id);
}
