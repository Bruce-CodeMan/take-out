package com.brucecompiler.impl;

import com.brucecompiler.OrdersService;
import com.brucecompiler.dto.OrdersSubmitDTO;
import com.brucecompiler.vo.OrderSubmitVO;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Override
    public OrderSubmitVO submit(OrdersSubmitDTO ordersSubmitDTO) {
        return null;
    }
}
