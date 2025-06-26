package com.brucecompiler.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.brucecompiler.SetMealService;
import com.brucecompiler.dto.SetMealDTO;
import com.brucecompiler.dto.SetMealPageQueryDTO;
import com.brucecompiler.result.PageResult;
import com.brucecompiler.result.Result;
import com.brucecompiler.vo.SetMealVO;

@RestController
@RequestMapping("/admin/setmeal")
public class SetMealController {

    private final SetMealService setMealService;

    @Autowired
    public SetMealController(SetMealService setMealService) {
        this.setMealService = setMealService;
    }

    @PostMapping
    public Result<Object> save(@RequestBody SetMealDTO setMealDTO) {
        setMealService.save(setMealDTO);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult<SetMealVO>> page(SetMealPageQueryDTO setMealPageQueryDTO) {
        PageResult<SetMealVO> pageResult = setMealService.page(setMealPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result<SetMealVO> getSetMealById(@PathVariable Long id) {
        SetMealVO setMealVO = setMealService.getByIdWithDish(id);
        return Result.success(setMealVO);
    }

    @PutMapping
    public Result<Object> update(@RequestBody SetMealDTO setMealDTO) {
        setMealService.update(setMealDTO);
        return Result.success();
    }
}
