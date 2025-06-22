package com.brucecompiler.mapper;

import com.brucecompiler.anno.AutoFill;
import com.brucecompiler.dto.CategoryPageQueryDTO;
import com.brucecompiler.entity.Category;
import com.brucecompiler.enumeration.OperationType;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Category Data Access Layer Interface
 * Responsible for interactions between {@link Category} entity and database table
 */
@Mapper
public interface CategoryMapper {

    /**
     * Retrieves a list of Category based on {@link CategoryPageQueryDTO}
     *
     * @param categoryDTO the data transfer object containing the page/pageSize/name/type
     * @return A list of {@link Category} object
     */
    Page<Category> page(CategoryPageQueryDTO categoryDTO);

    /**
     * Saves a new category info
     *
     * @param category The {@link Category} object
     */
    @AutoFill(OperationType.INSERT)
    void insert(Category category);

    /**
     * Updates the category based on the unique ID
     *
     * @param category the {@link Category} entity object
     */
    @AutoFill(OperationType.UPDATE)
    void update(Category category);

    /**
     * Delete the category based on the specified id
     *
     * @param id the unique id of the category entity
     */
    void delete(Long id);

    /**
     * Retrieves a list of the category based on the specified type
     *
     * @param type the type of the category
     * @return A list of the category
     */
    List<Category> list(Integer type);
}
