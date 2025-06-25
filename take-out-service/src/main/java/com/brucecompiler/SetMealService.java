package com.brucecompiler;

import com.brucecompiler.dto.SetMealDTO;
import com.brucecompiler.dto.SetMealPageQueryDTO;
import com.brucecompiler.result.PageResult;
import com.brucecompiler.vo.SetMealVO;

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
}
