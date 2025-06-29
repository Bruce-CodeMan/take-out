package com.brucecompiler.controller.user;

import com.brucecompiler.ShoppingCartService;
import com.brucecompiler.dto.ShoppingCartDTO;
import com.brucecompiler.entity.ShoppingCart;
import com.brucecompiler.result.Result;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController()
@RequestMapping("/user/shoppingCart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/add")
    public Result<Object> addCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        log.info("Add cart {}", shoppingCartDTO);
        shoppingCartService.addCart(shoppingCartDTO);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<ShoppingCart>> listCart() {
        List<ShoppingCart> list = shoppingCartService.list();
        return Result.success(list);
    }

}
