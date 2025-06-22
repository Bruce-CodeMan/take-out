package com.brucecompiler.mapper;

import com.brucecompiler.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    void insertBatch(List<DishFlavor> dishFlavorList);
}
