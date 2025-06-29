package com.brucecompiler.impl;

import com.brucecompiler.ShoppingCartService;
import com.brucecompiler.context.BaseContext;
import com.brucecompiler.dto.ShoppingCartDTO;
import com.brucecompiler.entity.Dish;
import com.brucecompiler.entity.SetMeal;
import com.brucecompiler.entity.ShoppingCart;
import com.brucecompiler.mapper.DishMapper;
import com.brucecompiler.mapper.SetMealMapper;
import com.brucecompiler.mapper.ShoppingCartMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final DishMapper dishMapper;
    private final ShoppingCartMapper shoppingCartMapper;
    private final SetMealMapper setMealMapper;

    @Autowired
    public ShoppingCartServiceImpl(
            DishMapper dishMapper,
            ShoppingCartMapper shoppingCartMapper,
            SetMealMapper setMealMapper
    ) {
        this.dishMapper = dishMapper;
        this.shoppingCartMapper = shoppingCartMapper;
        this.setMealMapper = setMealMapper;
    }

    @Override
    public void addCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();

        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);

        // 判断该商品是否存在购物车中
        // 唯一条件: dishId + dishFlavor + userId
        shoppingCart.setUserId(BaseContext.getCurrentId());
        ShoppingCart cart = shoppingCartMapper.selectBy(shoppingCart);

        if(cart == null) {
            // 说明购物车没有该商品数据
            // 补充缺失的属性
            // 判断是新增套餐, 还是新增菜品
            if(shoppingCartDTO.getDishId() != null) {
                // 新增菜品
                Dish dish = dishMapper.selectById(shoppingCartDTO.getDishId());
                shoppingCart.setName(dish.getName());
                shoppingCart.setAmount(dish.getPrice());
                shoppingCart.setImage(dish.getImage());
            }else{
                // 新增套餐
                SetMeal setMeal = setMealMapper.getById(shoppingCart.getSetmealId());
                shoppingCart.setName(setMeal.getName());
                shoppingCart.setAmount(setMeal.getPrice());
                shoppingCart.setImage(setMeal.getImage());
            }
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());

            // 将商品数据存入购物车表中
            shoppingCartMapper.insert(shoppingCart);
        }else{
            // 说明该商品有相同的数据
            // 更新购物车
            cart.setNumber(cart.getNumber() + 1);
            shoppingCartMapper.update(cart);
        }


    }

    @Override
    public List<ShoppingCart> list() {
        return shoppingCartMapper.list(BaseContext.getCurrentId());
    }
}
