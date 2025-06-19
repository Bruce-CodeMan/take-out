package com.brucecompiler.dto;

import lombok.Data;

@Data
public class EmployPageQueryDTO {

    private String name;

    private int page = 1;

    private int pageSize = 10;
}
