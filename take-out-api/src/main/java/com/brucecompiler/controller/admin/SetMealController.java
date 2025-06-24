package com.brucecompiler.controller.admin;

import com.brucecompiler.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/setmeal")
public class SetMealController {


    @GetMapping("/page")
    public Result<Object> page() {
        return Result.success();
    }
}
