package com.brucecompiler;

import com.brucecompiler.dto.EmployPageQueryDTO;
import com.brucecompiler.dto.EmployeeDTO;
import com.brucecompiler.dto.EmployeeLoginDTO;
import com.brucecompiler.entity.Employee;
import com.brucecompiler.result.PageResult;

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

    /**
     * Query a list of the employee
     *
     * @param employPageQueryDTO The data transfer object containing the condition of the paginated query
     * @return PageResult containing the whole employee information
     */
    PageResult<Employee> page(EmployPageQueryDTO employPageQueryDTO);

    /**
     * Enable and Disable the status of an employee
     *
     * @param status the status to set for the employee(e.g., 1 for enable, 0 for disabled)
     * @param id the unique identifier of the employee whose status needs to be updated
     */
    void enableEmployee(Integer status, Long id);

    /**
     * Retrieve the employee info based on ID
     *
     * @param id The unique id of the employee
     * @return {@link Employee} object if founds, null otherwise
     */
    Employee getById(Long id);

    /**
     * Updates the employee info based on the employee's ID
     *
     * @param employeeDTO The data transfer object containing the detailed info of the employee to be updated
     */
    void updateEmployee(EmployeeDTO employeeDTO);
}
