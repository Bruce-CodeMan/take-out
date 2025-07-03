package com.brucecompiler.mapper;

import com.brucecompiler.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersMapper {

    void insert(Orders orders);
}
