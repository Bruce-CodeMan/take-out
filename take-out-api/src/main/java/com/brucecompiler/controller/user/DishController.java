package com.brucecompiler.controller.user;

import java.util.List;

import com.brucecompiler.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.brucecompiler.DishService;
import com.brucecompiler.constant.StatusConstant;
import com.brucecompiler.entity.Dish;
import com.brucecompiler.result.Result;
import com.brucecompiler.vo.DishVO;

/**
 * Controller for managing dish
 * This controller providers RESTful APIs for dish-related operations
 *
 * <p>Example endpoints:</p>
 * - GET:   /list     Retrieve a list of dish
 */
@Slf4j
@RestController("UserDishController")
@RequestMapping("/user/dish")
public class DishController {

    private final DishService dishService;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public DishController(DishService dishService, RedisTemplate<String, Object> redisTemplate) {
        this.dishService = dishService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * Retrieve a list of dish
     *
     * @param categoryId The unique ID of the category
     * @return A list of dish
     */
    @GetMapping("/list")
    public Result<List<DishVO>> listByCategory(Long categoryId){

        String key = RedisConstant.DISH_REDIS + categoryId;
        // 缓存优化, 查询MySQL数据库之前, 先判断redis缓存中是否存在数据
        @SuppressWarnings("unchecked")
        List<DishVO> dishVOList = (List<DishVO>) redisTemplate.opsForValue().get(key);
        if(dishVOList != null && !dishVOList.isEmpty()){
            log.info("query the dish by category id:{} from the redis", categoryId);
            return Result.success(dishVOList);
        }
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(StatusConstant.ENABLED);

        dishVOList = dishService.listWithFlavor(dish);
        // 保存到redis中
        log.info("Set the dish by category id:{} into the redis", categoryId);
        redisTemplate.opsForValue().set(key, dishVOList);
        log.info("Query the dish by category id: {} from the database", categoryId);
        return Result.success(dishVOList);
    }
}
