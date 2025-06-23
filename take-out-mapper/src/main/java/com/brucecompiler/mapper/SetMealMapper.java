package com.brucecompiler.mapper;

import com.brucecompiler.anno.AutoFill;
import com.brucecompiler.entity.SetMeal;
import com.brucecompiler.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetMealMapper {

    /**
     * Retrieves the count of the set meal based on the specified category id
     *
     * @param categoryId the category id
     * @return the count of the set meal
     */
    Integer countByCategoryId(Long categoryId);

    /**
     * Retrieves a list of SetMeal objects based on the specified ID
     *
     * @param dishId The id of the dish for which associated SetMeal objects are to be retrieved
     * @return A list of SetMeal objects related to the given dish ID
     */
    List<SetMeal> getByDishId(Long dishId);

    /**
     * Updates an existing SetMeal object in the database
     *
     * @param setMeal The SetMeal object containing updated information to be saved
     */
    @AutoFill(OperationType.UPDATE)
    void update(SetMeal setMeal);
}
