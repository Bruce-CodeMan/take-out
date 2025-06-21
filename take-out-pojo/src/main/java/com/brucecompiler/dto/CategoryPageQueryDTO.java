package com.brucecompiler.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryPageQueryDTO implements Serializable {

     private int page = 1;

     private int pageSize = 10;

     private String name;

     // Type: 1 means type / 2 means set meal
     private Integer type;
}
