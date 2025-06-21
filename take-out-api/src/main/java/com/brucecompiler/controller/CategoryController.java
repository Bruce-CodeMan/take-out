package com.brucecompiler.controller;

import com.brucecompiler.CategoryService;
import com.brucecompiler.dto.CategoryDTO;
import com.brucecompiler.dto.CategoryPageQueryDTO;
import com.brucecompiler.entity.Category;
import com.brucecompiler.result.PageResult;
import com.brucecompiler.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/page")
    public Result<PageResult<Category>> page(CategoryPageQueryDTO categoryDTO) {
        PageResult<Category> pageResult = categoryService.page(categoryDTO);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result<Object> save(@RequestBody CategoryDTO categoryDTO) {
        categoryService.save(categoryDTO);
        return Result.success();
    }
}
