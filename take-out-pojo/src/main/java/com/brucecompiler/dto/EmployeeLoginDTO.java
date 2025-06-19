package com.brucecompiler.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class EmployeeLoginDTO implements Serializable {

    private String username;

    private String password;
}
