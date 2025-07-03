package com.brucecompiler.controller.user;

import com.brucecompiler.OrdersService;
import com.brucecompiler.dto.OrdersSubmitDTO;
import com.brucecompiler.result.Result;
import com.brucecompiler.vo.OrderSubmitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/order")
public class OrdersController {

    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping("/submit")
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO) {
        OrderSubmitVO vo = ordersService.submit(ordersSubmitDTO);
        return Result.success(vo);
    }
}
