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
public class ShoppingCart implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Long dishId;

    private Long setmealId;

    private Long userId;

    private String dishFlavor;

    private Integer number;

    private BigDecimal amount;

    private String image;

    private LocalDateTime createTime;
}
