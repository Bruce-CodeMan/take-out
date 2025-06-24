package com.brucecompiler.mapper;

import com.brucecompiler.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * Insert the {@link User} object into the database
     *
     * @param user the {@link User} entity object
     */
    void insert(User user);

    /**
     * Retrieves a {@link User} entity object from the database
     *
     * @param id the unique id of the {@link User} entity object
     * @return the {@link User} entity object
     */
    User selectById(Long id);

    /**
     * Retrieves a {@link User} entity object from the database
     *
     * @param openId the unique openId of the {@link User} entity object
     * @return the {@link User} entity object
     */
    User selectByOpenId(String openId);
}
