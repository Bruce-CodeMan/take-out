package com.brucecompiler.controller.user;

import com.brucecompiler.SetMealService;
import com.brucecompiler.constant.StatusConstant;
import com.brucecompiler.entity.SetMeal;
import com.brucecompiler.result.Result;
import com.brucecompiler.vo.DishItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userSetMealController")
@RequestMapping("/user/setmeal")
public class SetMealController {

    private final SetMealService setMealService;

    @Autowired
    public SetMealController(SetMealService setMealService) {
        this.setMealService = setMealService;
    }

    @Cacheable(cacheNames = "setmeal", key = "#categoryId")
    @GetMapping("/list")
    public Result<List<SetMeal>> getSetMeal(Long categoryId) {
        SetMeal setMeal = new SetMeal();
        setMeal.setCategoryId(categoryId);
        setMeal.setStatus(StatusConstant.ENABLED);

        List<SetMeal> list = setMealService.list(setMeal);
        return Result.success(list);
    }

    @GetMapping("/dish/{id}")
    public Result<List<DishItemVO>> dishList(@PathVariable("id") Long id) {
        List<DishItemVO> list = setMealService.getDishItemById(id);
        return Result.success(list);
    }
}
