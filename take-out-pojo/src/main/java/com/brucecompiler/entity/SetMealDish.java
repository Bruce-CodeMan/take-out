package com.brucecompiler.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SetMealDish implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    // 套餐id
    private Long setmealId;

    // 菜品id
    private Long dishId;

    // 菜品名称(冗余字段)
    private String name;

    // 菜品原价
    private BigDecimal price;

    // 份数
    private Integer copies;
}
