package com.brucecompiler.dto;

import lombok.Data;

@Data
public class DishPageQueryDTO {

    private int page = 1;

    private int pageSize = 10;

    private String name;

    private Integer categoryId;

    private Integer status;
}
