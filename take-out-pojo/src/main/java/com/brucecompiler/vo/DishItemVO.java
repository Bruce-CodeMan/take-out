package com.brucecompiler.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishItemVO {

    private String name;

    private Integer copies;

    private String image;

    private String description;
}
