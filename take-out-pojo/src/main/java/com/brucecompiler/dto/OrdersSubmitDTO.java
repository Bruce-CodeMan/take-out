package com.brucecompiler.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrdersSubmitDTO implements Serializable {

    private Long addressBookId;

    private Integer payMethod;

    private String remark;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime estimatedDeliveryTime;

    private Integer deliveryStatus;

    private Integer tablewareNumber;

    private Integer tablewareStatus;

    // 打包费
    private Integer packAmount;

    // 总金额
    private BigDecimal amount;
}
