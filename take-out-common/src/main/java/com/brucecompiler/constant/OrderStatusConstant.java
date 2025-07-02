package com.brucecompiler.constant;

public class OrderStatusConstant {

    // 1. 待付款
    public static final Integer PENDING_PAYMENT = 1;

    // 2. 待接单
    public static final Integer TO_BE_CONFIRMED = 2;

    // 3. 已接单
    public static final Integer CONFIRMED = 3;

    // 4. 派送中
    public static final Integer DELIVERY_IN_PROGRESS = 4;

    // 5. 已完成
    public static final Integer COMPLETED = 5;

    // 6. 已取消
    public static final Integer CANCELLED = 6;
}
