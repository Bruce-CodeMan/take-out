package com.brucecompiler.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.brucecompiler.EmployeeService;
import com.brucecompiler.constant.MessageConstant;
import com.brucecompiler.constant.StatusCodeConstant;
import com.brucecompiler.constant.StatusConstant;
import com.brucecompiler.dto.EmployeeLoginDTO;
import com.brucecompiler.entity.Employee;
import com.brucecompiler.exception.AccountLockedException;
import com.brucecompiler.exception.LoginErrorException;
import com.brucecompiler.mapper.EmployeeMapper;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {

        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        // 1. Find the data according to employee's username
        Employee employee = employeeMapper.findByName(username);

        // 2. Handling various exception
        if(employee == null) {
            throw new LoginErrorException(StatusCodeConstant.LOGIN_ERROR, MessageConstant.LOGIN_ERROR);
        }

        // 3. compare the password
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!password.equalsIgnoreCase(employee.getPassword())) {
            throw new LoginErrorException(StatusCodeConstant.LOGIN_ERROR, MessageConstant.LOGIN_ERROR);
        }

        // 4. Check the employee status
        if(Objects.equals(employee.getStatus(), StatusConstant.DISABLED)) {
            throw new AccountLockedException(StatusCodeConstant.ACCOUNT_LOCKED, MessageConstant.ACCOUNT_LOCKED);
        }
        return employee;
    }
}
