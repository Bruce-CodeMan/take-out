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

    /**
     * Retrieves the detailed dish information based on the ID
     *
     * @param id The id of the dish
     * @return the detailed dish
     */
    DishVO getById(Long id);

    /**
     * Updates the details of an existing dish in the database
     *
     * @param dishDTO The data transfer object containing the updated information for the dish
     */
    void update(DishDTO dishDTO);
}
