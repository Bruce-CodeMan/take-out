package com.brucecompiler.controller.user;

import com.brucecompiler.DishService;
import com.brucecompiler.constant.StatusConstant;
import com.brucecompiler.entity.Dish;
import com.brucecompiler.result.Result;
import com.brucecompiler.vo.DishVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("UserDishController")
@RequestMapping("/user/dish")
public class DishController {

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/list")
    public Result<List<DishVO>> listByCategory(Long categoryId){
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(StatusConstant.ENABLED);

        List<DishVO> dishVOList = dishService.listWithFlavor(dish);

        return Result.success(dishVOList);
    }
}
