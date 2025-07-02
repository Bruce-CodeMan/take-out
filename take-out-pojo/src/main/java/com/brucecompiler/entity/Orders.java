package com.brucecompiler.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Orders implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    // 订单编号
    private String number;

    // 订单状态
    private Integer status;

    // 下单用户
    private Long userId;

    // 地址id
    private Long addressBookId;

    // 下单时间
    private LocalDateTime orderTime;

    // 结账时间
    private LocalDateTime checkoutTime;

    // 支付方式
    private Integer payMethod;

    // 支付状态
    private Integer payStatus;

    // 实收金额
    private BigDecimal amount;

    // 备注
    private String remark;

    // 用户名
    private String userName;

    // 手机号
    private String phone;

    // 地址
    private String address;

    // 收货人
    private String consignee;

    // 订单取消原因
    private String cancelReason;

    // 订单拒绝原因
    private String rejectionReason;

    // 订单取消时间
    private LocalDateTime cancelTime;

    // 订单预计送达时间
    private LocalDateTime estimatedDeliveryTime;

    // 配送状态 1 立即送出,  0 选择具体时间
    private Integer deliveryStatus;

    // 送达时间
    private LocalDateTime deliveryTime;

    // 打包费
    private int packAmount;

    // 餐具数量
    private int tablewareNumber;

    // 参数数量状态
    private Integer tablewareStatus;
}
