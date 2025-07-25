package com.brucecompiler.impl;

import java.util.Objects;

import com.brucecompiler.dto.EmployPageQueryDTO;
import com.brucecompiler.result.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.brucecompiler.dto.EmployeeDTO;
import com.brucecompiler.properties.AdminProperties;
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
    private final AdminProperties adminProperties;

    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper, AdminProperties adminProperties) {
        this.employeeMapper = employeeMapper;
        this.adminProperties = adminProperties;
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

    @Override
    public void addEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();

        // Copy properties
        BeanUtils.copyProperties(employeeDTO, employee);
        // 1. Fill in missing attributes values
        employee.setPassword(DigestUtils.md5DigestAsHex(adminProperties.getPassword().getBytes()));
        employee.setStatus(StatusConstant.ENABLED);

//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());

        // Retrieves the current user's ID from the ThreadLocal
//        employee.setCreateUser(BaseContext.getCurrentId());
//        employee.setUpdateUser(BaseContext.getCurrentId());

        // 2. Call the mapper method, save the data into the MySQL
        employeeMapper.insert(employee);
    }

    @Override
    public PageResult<Employee> page(EmployPageQueryDTO employPageQueryDTO) {
        // 1. Set the parameters of paginated query
        PageHelper.startPage(employPageQueryDTO.getPage(), employPageQueryDTO.getPageSize());

        // 2. Call the mapper's  query function
        Page<Employee> page = employeeMapper.list(employPageQueryDTO.getName());


        // 3. Encapsulates the object and returns it
        return new PageResult<>(page.getTotal(), page.getResult());
    }

    @Override
    public void enableEmployee(Integer status, Long id) {
        Employee employee = Employee.builder()
                .id(id)
                .status(status)
//                .updateTime(LocalDateTime.now())
//                .updateUser(BaseContext.getCurrentId())
                .build();

        employeeMapper.update(employee);
    }

    @Override
    public Employee getById(Long id) {
        return employeeMapper.findById(id);
    }

    @Override
    public void updateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        // 拷贝属性值
        BeanUtils.copyProperties(employeeDTO, employee);
        employeeMapper.update(employee);
    }
}
