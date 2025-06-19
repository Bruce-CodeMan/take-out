package com.brucecompiler.controller;

import com.brucecompiler.EmployeeService;
import com.brucecompiler.constant.JwtClaimsConstant;
import com.brucecompiler.dto.EmployeeLoginDTO;
import com.brucecompiler.entity.Employee;
import com.brucecompiler.properties.JwtProperties;
import com.brucecompiler.result.Result;
import com.brucecompiler.utils.JwtUtil;
import com.brucecompiler.vo.EmployeeLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/admin/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final JwtProperties jwtProperties;

    @Autowired
    public EmployeeController(EmployeeService employeeService, JwtProperties jwtProperties) {
        this.employeeService = employeeService;
        this.jwtProperties = jwtProperties;
    }

    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {

        Employee employee = employeeService.login(employeeLoginDTO);

        // Login successfully, Generate the JWT token
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_IP, employee.getId());
        String token = JwtUtil.generateJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getExpirationTime(),
                claims
        );

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .username(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();
        return Result.success(employeeLoginVO);
    }
}
