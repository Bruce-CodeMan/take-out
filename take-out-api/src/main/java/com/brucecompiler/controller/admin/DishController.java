package com.brucecompiler.controller.admin;

import com.brucecompiler.DishService;
import com.brucecompiler.dto.DishDTO;
import com.brucecompiler.dto.DishPageQueryDTO;
import com.brucecompiler.entity.Dish;
import com.brucecompiler.result.PageResult;
import com.brucecompiler.result.Result;
import com.brucecompiler.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/dish")
public class DishController {

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping
    public Result<Object> addDish(@RequestBody DishDTO dishDTO) {
        dishService.addDish(dishDTO);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult<DishVO>> page(DishPageQueryDTO dishPageQueryDTO) {
        PageResult<DishVO> pageResult = dishService.page(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping
    public Result<Object> delete(@RequestParam List<Long> ids) {
        dishService.deleteDish(ids);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    public Result<Object> startOrStop(@PathVariable Integer status, Long id) {
        dishService.startOrStop(status, id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<DishVO> getById(@PathVariable Long id) {
        DishVO dishVO = dishService.getById(id);
        return Result.success(dishVO);
    }

    @PutMapping
    public Result<Object> update(@RequestBody DishDTO dishDTO) {
        dishService.update(dishDTO);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Dish>> listByCategoryIdOrName(Long categoryId, String name) {
        List<Dish> dishList = dishService.getByCategoryIdOrName(categoryId, name);
        return Result.success(dishList);
    }
}
