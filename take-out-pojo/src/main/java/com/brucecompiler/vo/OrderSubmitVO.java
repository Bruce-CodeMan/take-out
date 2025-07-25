package com.brucecompiler.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderSubmitVO implements Serializable {

    private Long id;

    private String orderNumber;

    private BigDecimal orderAmount;

    private LocalDateTime orderTime;
}
