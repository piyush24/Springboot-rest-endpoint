package com.springbootRest.challenge.rest_api.service;

import com.springbootRest.challenge.rest_api.entity.Employee;
import com.springbootRest.challenge.rest_api.exceptions.EmpDoesNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import java.util.UUID;

import static com.springbootRest.challenge.rest_api.constants.LoggerConstant.*;

@Service
public class EmpServiceImpl implements EmpService {
    @PersistenceContext
    private EntityManager entityManager;
    private Logger logger = LoggerFactory.getLogger(EmpServiceImpl.class);

    /**
     * Gets the user for Id.
     *
     * @return Optional of Employee
     */
    @Override
    @Transactional
    public Optional<Employee> findEmpById(String id) {
        logger.debug(LOGGER_SERVICE_STATEMENT_1001, id);
        Employee employee = entityManager.find(Employee.class, id);
        if (employee != null) {
            logger.debug(LOGGER_SERVICE_STATEMENT_1002, employee.toString());
        } else {
            logger.debug(LOGGER_SERVICE_STATEMENT_1003, id);
        }
        return Optional.ofNullable(employee);
    }

    @Override
    @Transactional
    public Employee saveEmp(Employee emp) {
        logger.debug(LOGGER_SERVICE_STATEMENT_1005, emp.toString());
        emp.setId(UUID.randomUUID().toString());
        entityManager.persist(emp);
        return emp;
    }

    @Override
    @Transactional
    public Employee updateEmp(Employee emp) {
        logger.debug(LOGGER_SERVICE_STATEMENT_1008, emp.toString());
        Optional<Employee> employeeOptional = findEmpById(emp.getId());
        if (!employeeOptional.isPresent()) {
            throw new EmpDoesNotExistException();
        }
        entityManager.persist(emp);
        return emp;
    }
}
