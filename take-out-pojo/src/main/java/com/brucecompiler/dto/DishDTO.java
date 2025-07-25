package com.brucecompiler.dto;

import com.brucecompiler.entity.DishFlavor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDTO implements Serializable {

    private Long id;

    private String name;

    private Long categoryId;

    private BigDecimal price;

    private String image;

    private String description;

    private Integer status;

    private List<DishFlavor> flavors = new ArrayList<>();
}
