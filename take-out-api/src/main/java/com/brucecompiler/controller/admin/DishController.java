package com.brucecompiler.controller.admin;

import com.brucecompiler.DishService;
import com.brucecompiler.dto.DishDTO;
import com.brucecompiler.dto.DishPageQueryDTO;
import com.brucecompiler.result.PageResult;
import com.brucecompiler.result.Result;
import com.brucecompiler.vo.DishVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
