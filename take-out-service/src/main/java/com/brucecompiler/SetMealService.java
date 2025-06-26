package com.brucecompiler;

import com.brucecompiler.dto.SetMealDTO;
import com.brucecompiler.dto.SetMealPageQueryDTO;
import com.brucecompiler.entity.SetMeal;
import com.brucecompiler.result.PageResult;
import com.brucecompiler.vo.DishItemVO;
import com.brucecompiler.vo.SetMealVO;

import java.util.List;

public interface SetMealService {

    /**
     * Save a new set meal into the database
     *
     * @param setMealDTO the data transfer object containing the set meal information to be saved
     */
    void save(SetMealDTO setMealDTO);

    /**
     * Perform a paginated query for set meal information
     *
     * @param setMealPageQueryDTO the data transfer object containing the query condition
     * @return A paginated result for a list of set meal
     */
    PageResult<SetMealVO> page(SetMealPageQueryDTO setMealPageQueryDTO);

    /**
     * Retrieve an object containing the set meal and set meal dish
     *
     * @param id The unique ID of the set meal
     * @return An object containing the set meal and set meal dish
     */
    SetMealVO getByIdWithDish(Long id);

    /**
     * Update a set meal info
     *
     * @param setMealDTO the data transfer object containing the info to be updated
     */
    void update(SetMealDTO setMealDTO);

    /**
     * Start or Stop a set meal status
     *
     * @param status The status of the set meal
     * @param id The unique ID of the set meal
     */
    void startOrStop(Integer status, Long id);


    /**
     * Delete a list of set meal
     *
     * @param ids A list of IDs
     */
    void delete(List<Long> ids);

    /**
     * Retrieve a list of set meal
     *
     * @param setMeal a set meal entity object to be queried
     * @return A list of set meal
     */
    List<SetMeal> list(SetMeal setMeal);

    /**
     * Retrieve a list of dish item based on set meal id
     *
     * @param id The unique ID of the set meal
     * @return A list of dish item
     */
    List<DishItemVO> getDishItemById(Long id);
}
