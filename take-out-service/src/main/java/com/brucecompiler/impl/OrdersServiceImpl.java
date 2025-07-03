package com.brucecompiler.impl;

import com.brucecompiler.OrdersService;
import com.brucecompiler.constant.MessageConstant;
import com.brucecompiler.constant.OrderPaymentConstant;
import com.brucecompiler.constant.OrderStatusConstant;
import com.brucecompiler.constant.StatusCodeConstant;
import com.brucecompiler.context.BaseContext;
import com.brucecompiler.dto.OrdersSubmitDTO;
import com.brucecompiler.entity.*;
import com.brucecompiler.exception.OrderBusinessException;
import com.brucecompiler.mapper.*;
import com.brucecompiler.vo.OrderSubmitVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    private final AddressMapper addressMapper;
    private final UserMapper userMapper;
    private final OrdersMapper ordersMapper;
    private final ShoppingCartMapper shoppingCartMapper;
    private final OrderDetailMapper orderDetailMapper;

    @Autowired
    public OrdersServiceImpl(
            AddressMapper addressMapper,
            UserMapper userMapper,
            OrdersMapper ordersMapper,
            ShoppingCartMapper shoppingCartMapper,
            OrderDetailMapper orderDetailMapper
    ) {
        this.addressMapper = addressMapper;
        this.userMapper = userMapper;
        this.ordersMapper = ordersMapper;
        this.shoppingCartMapper = shoppingCartMapper;
        this.orderDetailMapper = orderDetailMapper;
    }

    /**
     *  最终目的: 将订单数据存入表中(orders, order_detail)
     * @param ordersSubmitDTO the data transfer object containing the info of the user submit the order
     * @return the view object containing the order number
     */
    @Override
    @Transactional
    public OrderSubmitVO submit(OrdersSubmitDTO ordersSubmitDTO) {

        // 1.2查询地址表, 获取收货人信息
        AddressBook addressBook = addressMapper.findById(ordersSubmitDTO.getAddressBookId());
        if(addressBook == null) {
            throw new OrderBusinessException(StatusCodeConstant.FAILURE, MessageConstant.ADDRESS_BOOK_IS_NULL);
        }

        // 1.4查询用户表, 获取用户相关信息
        Long userId = BaseContext.getCurrentId();
        User user = userMapper.selectById(userId);
        if(user == null) {
            throw new OrderBusinessException(StatusCodeConstant.FAILURE, MessageConstant.USER_NOT_LOGIN);
        }
        // 2.1查询购物车列表, 查询自己名下的购物车数据
        List<ShoppingCart> cartList = shoppingCartMapper.list(userId);
        if(cartList == null || cartList.isEmpty()) {
            throw new OrderBusinessException(StatusCodeConstant.FAILURE, MessageConstant.SHOPPING_CART_IS_NULL);
        }

        // 1. 存入order表
        Orders orders = new Orders();
        BeanUtils.copyProperties(ordersSubmitDTO, orders);

        // 1.1补充缺失值
        orders.setNumber(System.currentTimeMillis() + "");              // 订单编号
        orders.setStatus(OrderStatusConstant.PENDING_PAYMENT);          // 待付款, 订单状态
        orders.setUserId(userId);                                       // 下单人ID
        orders.setOrderTime(LocalDateTime.now());                       // 下单时间
        orders.setPayStatus(OrderPaymentConstant.UN_PAID);              // 未支付，支付状态
        // 1.3继续补充
        orders.setPhone(addressBook.getPhone());                        // 收货人手机号
        String detail = addressBook.getProvinceName()
                + addressBook.getCityName()
                + addressBook.getDistrictName()
                + addressBook.getDetail();
        orders.setAddress(detail);                                      // 收获地址
        orders.setConsignee(addressBook.getConsignee());                // 收货人姓名
        // 1.5继续补充
        orders.setUserName(user.getName());                             // 下单人姓名
        // 1.6插入到表中
        ordersMapper.insert(orders);

        // 2. 构造订单明细数据,存入order_detail表中
        List<OrderDetail> orderDetailList = new ArrayList<>();
        // 2.2循环遍历购物车列表数据, 构造订单明细
        cartList.forEach(cart -> {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(cart, orderDetail, "id");      // 不复制id这个字段, 忽略
            // 关联订单id
            orderDetail.setOrderId(orders.getId());
            orderDetailList.add(orderDetail);
        });
        // 批量插入订单明细数据
        orderDetailMapper.insertBatch(orderDetailList);

        // 3. 清空购物车, 删除自己名下的购物车列表数据
        shoppingCartMapper.clean(userId);

        // 4. 构造OrderSubmitVO对象, 并返回
        return OrderSubmitVO.builder()
                .id(orders.getId())
                .orderNumber(orders.getNumber())
                .orderAmount(orders.getAmount())
                .orderTime(orders.getOrderTime())
                .build();
    }
}
