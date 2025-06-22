package com.brucecompiler;

import com.brucecompiler.dto.DishDTO;
import com.brucecompiler.entity.Dish;

public interface DishService {

    /**
     * Add a new dish
     *
     * @param dishDTO the data transfer object containing the info to be added
     */
    void addDish(DishDTO dishDTO);
}
