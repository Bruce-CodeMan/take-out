package com.brucecompiler.mapper;

import com.brucecompiler.anno.AutoFill;
import com.brucecompiler.dto.DishPageQueryDTO;
import com.brucecompiler.entity.Dish;
import com.brucecompiler.enumeration.OperationType;
import com.brucecompiler.vo.DishVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    /**
     * Retrieve a dish based on id
     *
     * @param id the id of the dish to be retrieved
     * @return The dish corresponding to the given ID
     */
    Dish selectById(Long id);

    /**
     * Delete the dishes based on the list of id
     *
     * @param ids The list of the dishes id
     */
    void deleteBatch(List<Long> ids);

    /**
     * Update the dish info based on its ID
     *
     * @param dish the {@link Dish} entity object
     */
    @AutoFill(OperationType.UPDATE)
    void update(Dish dish);

    /**
     * Retrieves a list of dishes filtered by category and status
     *
     * @param dish The dish object containing the category and status
     * @return A list of dishes which match the specified category and status
     */
    List<Dish> listByCategoryAndStatus(Dish dish);

    /**
     * Retrieves a list of dishes filtered by categoryId and name
     *
     * @param categoryId The unique id of the category
     * @param name The dish name
     * @return A list of dishes which match the specified category and name
     */
    List<Dish> listByCategoryAndName(Long categoryId, String name);
}
