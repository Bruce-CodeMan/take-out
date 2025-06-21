package com.brucecompiler.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    // Type: 1 means dish type, 2 means set meal type
    private Integer type;

    private String name;

    private Integer sort;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long createUser;

    private Long updateUser;
}
