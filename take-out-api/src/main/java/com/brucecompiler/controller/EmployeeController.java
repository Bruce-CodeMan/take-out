package com.brucecompiler.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.brucecompiler.EmployeeService;
import com.brucecompiler.constant.JwtClaimsConstant;
import com.brucecompiler.dto.EmployeeDTO;
import com.brucecompiler.dto.EmployeeLoginDTO;
import com.brucecompiler.entity.Employee;
import com.brucecompiler.properties.JwtProperties;
import com.brucecompiler.result.Result;
import com.brucecompiler.utils.JwtUtil;
import com.brucecompiler.vo.EmployeeLoginVO;

/**
 * EmployeeController
 * This controller is responsible for handling HTTP requests related to employee management
 */
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

    /**
     * Employee login endpoint
     * This method validates the employee login credentials, generates a JWT token
     *
     * @param employeeLoginDTO Data Transfer Object containing login credentials(username & password)
     * @return A result object containing EmployeeLoginVO, which includes employee details
     */
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

    /**
     * Add a new employee endpoint
     * This method handles the creation of a new employee based on the provided employee data
     *
     * @param employeeDTO Data Transfer Object containing the details of the employee to be added
     * @return A generic success Result object indicating the employee was added successfully
     */
    @PostMapping
    public Result<Object> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.addEmployee(employeeDTO);
        return Result.success();
    }
}
