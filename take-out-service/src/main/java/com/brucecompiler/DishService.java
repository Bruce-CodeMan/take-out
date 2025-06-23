package com.brucecompiler;

import com.brucecompiler.dto.DishDTO;
import com.brucecompiler.dto.DishPageQueryDTO;
import com.brucecompiler.entity.Dish;
import com.brucecompiler.result.PageResult;
import com.brucecompiler.vo.DishVO;

import java.util.List;

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
     * @param dishPageQueryDTO the data transfer object containing the fields of the paginated query
     */
    PageResult<DishVO> page(DishPageQueryDTO dishPageQueryDTO);

    /**
     * Delete one or more dishes based on IDS
     *
     * @param ids List of the dish IDs to be deleted
     */
    void deleteDish(List<Long> ids);

    /**
     * Start or Stop the status of the dish
     *
     * @param status the status of the dish
     * @param id the unique id of dish
     */
    void startOrStop(Integer status, Long id);
}
