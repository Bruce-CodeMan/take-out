package com.brucecompiler.controller.admin;

import com.brucecompiler.DishService;
import com.brucecompiler.constant.RedisConstant;
import com.brucecompiler.dto.DishDTO;
import com.brucecompiler.dto.DishPageQueryDTO;
import com.brucecompiler.entity.Dish;
import com.brucecompiler.result.PageResult;
import com.brucecompiler.result.Result;
import com.brucecompiler.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/admin/dish")
public class DishController {

    private final DishService dishService;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public DishController(
            DishService dishService,
            RedisTemplate<String, Object> redisTemplate
    ) {
        this.dishService = dishService;
        this.redisTemplate = redisTemplate;
    }

    @PostMapping
    public Result<Object> addDish(@RequestBody DishDTO dishDTO) {
        dishService.addDish(dishDTO);
        // Remove the data from the redis
        redisTemplate.delete(RedisConstant.DISH_REDIS + dishDTO.getCategoryId());
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

        // 删除所有缓存
        Set<String> keys = redisTemplate.keys(RedisConstant.DISH_REDIS + "*");
        redisTemplate.delete(keys);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    public Result<Object> startOrStop(@PathVariable Integer status, Long id) {
        dishService.startOrStop(status, id);
        Set<String> keys = redisTemplate.keys(RedisConstant.DISH_REDIS + "*");
        redisTemplate.delete(keys);
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
        Set<String> keys = redisTemplate.keys(RedisConstant.DISH_REDIS + "*");
        redisTemplate.delete(keys);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Dish>> listByCategoryIdOrName(Long categoryId, String name) {
        List<Dish> dishList = dishService.getByCategoryIdOrName(categoryId, name);
        return Result.success(dishList);
    }
}
