package com.brucecompiler.controller.admin;

import com.brucecompiler.constant.ShopStatusConstant;
import com.brucecompiler.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin/shop")
public class ShopController {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public ShopController(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PutMapping("/{status}")
    public Result<Object> changeStatus(@PathVariable Integer status) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(ShopStatusConstant.SHOP_STATUS, status);

        return Result.success();
    }

    @GetMapping("/status")
    public Result<Object> getStatus() {
        Integer status = (Integer) redisTemplate.opsForValue().get(ShopStatusConstant.SHOP_STATUS);
        return Result.success(status);
    }
}
