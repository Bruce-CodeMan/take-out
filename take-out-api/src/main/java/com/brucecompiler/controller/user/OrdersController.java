package com.brucecompiler.controller.user;

import com.brucecompiler.dto.OrdersSubmitDTO;
import com.brucecompiler.result.Result;
import com.brucecompiler.vo.OrderSubmitVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/order")
public class OrdersController {

    @PostMapping("/submit")
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO) {

        return Result.success();
    }
}
