package com.brucecompiler.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {

    private Long id;

    private String name;

    private Integer sort;

    private Integer type;
}
