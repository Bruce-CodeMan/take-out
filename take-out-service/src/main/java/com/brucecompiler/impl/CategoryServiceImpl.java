package com.brucecompiler.impl;

import com.brucecompiler.CategoryService;
import com.brucecompiler.constant.MessageConstant;
import com.brucecompiler.constant.StatusCodeConstant;
import com.brucecompiler.constant.StatusConstant;
import com.brucecompiler.context.BaseContext;
import com.brucecompiler.dto.CategoryDTO;
import com.brucecompiler.dto.CategoryPageQueryDTO;
import com.brucecompiler.entity.Category;
import com.brucecompiler.exception.DeletionNotAllowedException;
import com.brucecompiler.mapper.CategoryMapper;
import com.brucecompiler.mapper.DishMapper;
import com.brucecompiler.mapper.SetMealMapper;
import com.brucecompiler.result.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final DishMapper dishMapper;
    private final SetMealMapper setMealMapper;

    @Autowired
    public CategoryServiceImpl(
            CategoryMapper categoryMapper,
            DishMapper dishMapper,
            SetMealMapper setMealMapper
    ) {
        this.categoryMapper = categoryMapper;
        this.dishMapper = dishMapper;
        this.setMealMapper = setMealMapper;
    }

    @Override
    public PageResult<Category> page(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        Page<Category> page = categoryMapper.page(categoryPageQueryDTO);

        return new PageResult<>(page.getTotal(), page.getResult());
    }

    @Override
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();

        BeanUtils.copyProperties(categoryDTO, category);

        // 分类状态默认为禁用状态0
        category.setStatus(StatusConstant.DISABLED);


        categoryMapper.insert(category);
    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);

        categoryMapper.update(category);
    }

    @Override
    public void startOrStop(Integer status, Long id) {
        Category category = Category.builder()
                .id(id)
                .status(status)
                .build();

        categoryMapper.update(category);
    }

    @Override
    public void deleteById(Long id) {
        Integer count = dishMapper.countByCategoryId(id);
        if(count > 0) {
            throw new DeletionNotAllowedException(
                    StatusCodeConstant.FAILURE,
                    MessageConstant.CATEGORY_BE_RELATED_BY_DISH
            );
        }

        count = setMealMapper.countByCategoryId(id);
        if(count > 0) {
            throw new DeletionNotAllowedException(
                    StatusCodeConstant.FAILURE,
                    MessageConstant.CATEGORY_BE_RELATED_BY_SET_MEAL
            );
        }
        categoryMapper.delete(id);
    }

    @Override
    public List<Category> list(Integer type) {
        return categoryMapper.list(type);
    }
}
