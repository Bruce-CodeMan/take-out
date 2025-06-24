package com.brucecompiler.controller.user;

import com.brucecompiler.constant.ShopStatusConstant;
import com.brucecompiler.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user/shop")
public class UserShopController {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public UserShopController(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/status")
    public Result<Object> getStatus() {
        Integer status = (Integer) redisTemplate.opsForValue().get(ShopStatusConstant.SHOP_STATUS);
        log.info("status: {}", status);
        return Result.success(status);
    }
}
