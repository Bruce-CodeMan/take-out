package com.brucecompiler.mapper;

import com.brucecompiler.anno.AutoFill;
import com.brucecompiler.dto.DishPageQueryDTO;
import com.brucecompiler.entity.Dish;
import com.brucecompiler.enumeration.OperationType;
import com.brucecompiler.vo.DishVO;
import com.github.pagehelper.Page;
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

    /**
     * Insert the dish object into the database table
     *
     * @param dish the {@link Dish} entity object
     */
    @AutoFill(OperationType.INSERT)
    void insert(Dish dish);

    /**
     * Retrieves a list of DishVO to show the frontend
     *
     * @param queryDTO the data transfer object containing the specified info to be selected
     * @return A list of DishVO
     */
    Page<DishVO> list(DishPageQueryDTO queryDTO);
}
