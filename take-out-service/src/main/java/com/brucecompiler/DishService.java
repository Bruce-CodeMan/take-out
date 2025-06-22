package com.brucecompiler;

import com.brucecompiler.dto.DishDTO;
import com.brucecompiler.dto.DishPageQueryDTO;
import com.brucecompiler.entity.Dish;
import com.brucecompiler.result.PageResult;
import com.brucecompiler.vo.DishVO;

public interface DishService {

    /**
     * Add a new dish
     *
     * @param dishDTO the data transfer object containing the info to be added
     */
    void addDish(DishDTO dishDTO);

    /**
     * Retrieves a list of the
     *
     * @param dishPageQueryDTO
     */
    PageResult<DishVO> page(DishPageQueryDTO dishPageQueryDTO);
}
