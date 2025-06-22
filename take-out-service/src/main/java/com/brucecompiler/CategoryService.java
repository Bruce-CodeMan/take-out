package com.brucecompiler;

import com.brucecompiler.dto.CategoryDTO;
import com.brucecompiler.dto.CategoryPageQueryDTO;
import com.brucecompiler.entity.Category;
import com.brucecompiler.result.PageResult;

import java.util.List;

public interface CategoryService {

    /**
     * Query a list of the category
     *
     * @param categoryPageQueryDTO The data transfer object containing the condition of the paginated query
     * @return A list of the {@link Category} object
     */
    PageResult<Category> page(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * Save a new category
     *
     * @param categoryDTO the data transfer object containing the info of the category to be added
     */
    void save(CategoryDTO categoryDTO);

    /**
     * Update the category based on specified id
     *
     * @param categoryDTO the data transfer object containing the info of the category to be updated
     */
    void update(CategoryDTO categoryDTO);

    /**
     * Start or Stop the status of the category
     *
     * @param status the status of the category
     * @param id the unique id of the category
     */
    void startOrStop(Integer status, Long id);

    /**
     * Delete a category info based on the specified id
     *
     * @param id the unique id of the category
     */
    void deleteById(Long id);

    /**
     * Retrieves a list of the category
     *
     * @param type the type of the category
     * @return A list of the category
     */
    List<Category> list(Integer type);
}
