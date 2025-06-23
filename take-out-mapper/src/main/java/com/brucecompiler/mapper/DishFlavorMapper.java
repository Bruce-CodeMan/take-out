package com.brucecompiler.mapper;

import com.brucecompiler.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    /**
     * Insert a batch of DishFlavor objects into the database
     *
     * @param dishFlavorList A list of DishFlavor objects to be inserted.Each object represents the flavor detail
     *                       of a dish
     */
    void insertBatch(List<DishFlavor> dishFlavorList);

    /**
     * Delete a batch of DishFlavor objects from the database based on their IDs
     *
     * @param ids A list of IDs representing the DishFlavor objects to be deleted
     */
    void deleteBatch(List<Long> ids);
}
