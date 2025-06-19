package com.brucecompiler;

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
}
