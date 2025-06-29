package com.brucecompiler.mapper;

import com.brucecompiler.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    ShoppingCart selectBy(ShoppingCart shoppingCart);

    void insert(ShoppingCart shoppingCart);

    void update(ShoppingCart shoppingCart);

    List<ShoppingCart> list(Long userId);
}
