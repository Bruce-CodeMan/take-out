package com.brucecompiler.dto;

import lombok.Data;

@Data
public class SetMealPageQueryDTO {

    private int page = 1;

    private int pageSize = 10;

    private String name;

    private Long categoryId;

    private Integer status;
}
