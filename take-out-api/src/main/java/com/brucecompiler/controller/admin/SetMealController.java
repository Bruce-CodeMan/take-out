package com.brucecompiler.controller.admin;

import com.brucecompiler.SetMealService;
import com.brucecompiler.dto.SetMealDTO;
import com.brucecompiler.result.Result;
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

    @GetMapping("/page")
    public Result<Object> page() {
        return Result.success();
    }


    @PostMapping
    public Result<Object> save(@RequestBody SetMealDTO setMealDTO) {
        setMealService.save(setMealDTO);
        return Result.success();
    }
}
