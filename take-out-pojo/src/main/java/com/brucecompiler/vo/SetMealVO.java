package com.brucecompiler.vo;

import com.brucecompiler.entity.SetMealDish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SetMealVO implements Serializable {

    private Long id;

    private Long categoryId;

    private String name;

    private BigDecimal price;

    private Integer status;

    private String description;

    private String image;

    private LocalDateTime updateTime;

    private String categoryName;

    private List<SetMealDish> setmealDishes = new ArrayList<>();
}
