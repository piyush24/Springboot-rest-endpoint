package com.springbootRest.challenge.rest_api.service;


import com.springbootRest.challenge.rest_api.entity.Employee;
import com.springbootRest.challenge.rest_api.exceptions.EmpDoesNotExistException;

import java.util.Optional;

public interface EmpService {

    /**
     * Gets the user for Id.
     *
     * @return Optional of Employee
     */
    Optional<Employee> findEmpById(String id);

    /**
     * Saves Employee.
     *
     * @return Saved Employee
     */
    Employee saveEmp(Employee employee);

    /**
     * updates Employee.
     *
     * @return Saved Employee
     * @throws EmpDoesNotExistException if employee does not exist
     */
    Employee updateEmp(Employee emp);
}
