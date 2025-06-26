package com.brucecompiler.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import com.brucecompiler.SetMealService;
import com.brucecompiler.dto.SetMealDTO;
import com.brucecompiler.dto.SetMealPageQueryDTO;
import com.brucecompiler.result.PageResult;
import com.brucecompiler.result.Result;
import com.brucecompiler.vo.SetMealVO;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
public class SetMealController {

    private final SetMealService setMealService;

    @Autowired
    public SetMealController(SetMealService setMealService) {
        this.setMealService = setMealService;
    }

    @CacheEvict(cacheNames = "setmeal", key = "#setMealDTO.categoryId")
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

    @CacheEvict(cacheNames = "setmeal", allEntries = true)
    @PutMapping
    public Result<Object> update(@RequestBody SetMealDTO setMealDTO) {
        setMealService.update(setMealDTO);
        return Result.success();
    }

    @CacheEvict(cacheNames = "setmeal", allEntries = true)
    @PostMapping("/status/{status}")
    public Result<Object> startOrStop(@PathVariable Integer status, Long id) {
        setMealService.startOrStop(status, id);
        return Result.success();
    }

    @CacheEvict(cacheNames = "setmeal", allEntries = true)
    @DeleteMapping
    public Result<Object> delete(@RequestParam List<Long> ids) {
        setMealService.delete(ids);
        return Result.success();
    }
}
