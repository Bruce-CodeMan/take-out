package com.brucecompiler;

import com.brucecompiler.dto.CategoryPageQueryDTO;
import com.brucecompiler.entity.Category;
import com.brucecompiler.result.PageResult;

public interface CategoryService {

    /**
     * Query a list of the category
     *
     * @param categoryPageQueryDTO The data transfer object containing the condition of the paginated query
     * @return A list of the {@link Category} object
     */
    PageResult<Category> page(CategoryPageQueryDTO categoryPageQueryDTO);
}
