package com.brucecompiler;

import com.brucecompiler.dto.ShoppingCartDTO;
import com.brucecompiler.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    void addCart(ShoppingCartDTO shoppingCartDTO);

    List<ShoppingCart> list();

    void cleanCart();

    void subCart(ShoppingCartDTO shoppingCartDTO);

}
