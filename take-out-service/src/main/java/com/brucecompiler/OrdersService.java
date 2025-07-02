package com.brucecompiler;

import com.brucecompiler.dto.OrdersSubmitDTO;
import com.brucecompiler.vo.OrderSubmitVO;

public interface OrdersService {

    OrderSubmitVO submit(OrdersSubmitDTO ordersSubmitDTO);
}
