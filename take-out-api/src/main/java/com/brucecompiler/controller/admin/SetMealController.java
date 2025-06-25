package com.brucecompiler.controller.admin;

import com.brucecompiler.SetMealService;
import com.brucecompiler.dto.SetMealDTO;
import com.brucecompiler.dto.SetMealPageQueryDTO;
import com.brucecompiler.result.PageResult;
import com.brucecompiler.result.Result;
import com.brucecompiler.vo.SetMealVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
