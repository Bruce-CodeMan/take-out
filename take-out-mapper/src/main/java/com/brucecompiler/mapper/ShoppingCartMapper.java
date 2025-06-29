package com.brucecompiler.mapper;

import com.brucecompiler.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShoppingCartMapper {

    ShoppingCart selectBy(ShoppingCart shoppingCart);

    void insert(ShoppingCart shoppingCart);

    void update(ShoppingCart shoppingCart);
}
