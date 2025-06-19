package com.brucecompiler;

import com.brucecompiler.dto.EmployeeDTO;
import com.brucecompiler.dto.EmployeeLoginDTO;
import com.brucecompiler.entity.Employee;

/**
 * Service interface for managing employee-related operations
 *
 * @author Bruce Compiler
 */
public interface EmployeeService {

    /**
     * Authenticates an employee
     *
     * @param employeeLoginDTO The data transfer object containing login credentials (username & password)
     * @return {@link Employee} object if founds, null otherwise
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * Add a new employee based on the provided employee data
     *
     * @param employeeDTO The data transfer object containing the details of the employee to be added
     */
    void addEmployee(EmployeeDTO employeeDTO);
}
