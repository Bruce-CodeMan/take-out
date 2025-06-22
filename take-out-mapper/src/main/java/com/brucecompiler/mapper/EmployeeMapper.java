package com.brucecompiler.mapper;

import java.util.List;

import com.brucecompiler.anno.AutoFill;
import com.brucecompiler.enumeration.OperationType;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import com.brucecompiler.entity.Employee;

/**
 * Employee Data Access Layer Interface
 * Responsible for interactions between {@link Employee} entity and database table
 * Corresponding XML mapping file is located at resources/mapper/EmployeeMapper.xml
 *
 * @author Bruce Compiler
 */
@Mapper
public interface EmployeeMapper {

    /**
     * Retrieves a list of all employee
     *
     * @return A list of {@link Employee} objects representing all employees
     */
    List<Employee> findAll();

    /**
     * Retrieve an {@link Employee} object based on the specified ID
     *
     * @param id The ID od the {@link Employee} (primary key)
     * @return {@link Employee} object if found, null otherwise
     */
    Employee findById(Long id);

    /**
     * Saves a new {@link Employee} object
     *
     * @param employee The {@link Employee} object to be saved
     * @return Number of rows affected, returns 1 if successful, 0 otherwise
     */
    @AutoFill(OperationType.INSERT)
    int insert(Employee employee);

    /**
     * Updates an {@link Employee} object
     *
     * @param employee The {@link Employee} object to be updated
     * @return Number of rows affected, returns 1 if successful, 0 otherwise
     */
    @AutoFill(OperationType.UPDATE)
    int update(Employee employee);

    /**
     * Deletes an {@link Employee} object
     *
     * @param id The ID of the {@link Employee} object
     * @return Number of rows affected, returns 1 if successful, 0 otherwise
     */
    int deleteById(Long id);

    /**
     * Retrieves an {@link Employee} object based on specified username
     *
     * @param username The username of {@link Employee} to search for
     * @return {@link Employee} object if founds, null otherwise
     */
    Employee findByName(String username);

    /**
     * Retrieves an {@link Employee} object list based on specified name
     *
     * @param name The chinese name
     */
    Page<Employee> list(String name);
}
