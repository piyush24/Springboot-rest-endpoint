package com.springbootRest.challenge.rest_api.controller;


import com.springbootRest.challenge.rest_api.entity.Employee;
import com.springbootRest.challenge.rest_api.helper.MessageHelperService;
import com.springbootRest.challenge.rest_api.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.springbootRest.challenge.rest_api.constants.ConfigConstant.REST_API_URI_EMPLOYEE;
import static com.springbootRest.challenge.rest_api.constants.ConfigConstant.REST_API_URI_EMPLOYEE_ID;
import static com.springbootRest.challenge.rest_api.constants.LoggerConstant.LOGGER_SERVICE_STATEMENT_1004;
import static com.springbootRest.challenge.rest_api.constants.LoggerConstant.LOGGER_SERVICE_STATEMENT_1006;
import static com.springbootRest.challenge.rest_api.constants.MessageConstants.RECORD_NOT_FOUND_ID;
import static com.springbootRest.challenge.rest_api.constants.MessageConstants.SERVER_INTERNAL_ERROR;
import static org.springframework.http.ResponseEntity.status;

@RestController()
@RequestMapping(REST_API_URI_EMPLOYEE)
public class EmployeeController {
    @Autowired
    private EmpService empService;
    @Autowired
    private MessageHelperService messageHelperService;
    private Logger logger = LoggerFactory.getLogger(Employee.class);

    /**
     * get the employee.
     *
     * @return ResponseEntity with employee
     */
    @GetMapping(REST_API_URI_EMPLOYEE_ID)
    public ResponseEntity findEmpById(@PathVariable("id") String id) {
        logger.debug(LOGGER_SERVICE_STATEMENT_1004, id);
        try {
            Optional<Employee> employeeOptional = empService.findEmpById(id);
            if (employeeOptional.isPresent()) {
                return status(HttpStatus.OK).body(employeeOptional.get());
            }
            return status(HttpStatus.NOT_FOUND).body(messageHelperService.getMessage(RECORD_NOT_FOUND_ID, id));
        } catch (Exception e) {
            return status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageHelperService.getMessage(SERVER_INTERNAL_ERROR));
        }
    }

    @PostMapping()
    public ResponseEntity saveEmployee(@RequestBody Employee employee) {
        logger.debug(LOGGER_SERVICE_STATEMENT_1006, employee.toString());
        try {
            employee = empService.saveEmp(employee);
            return status(HttpStatus.OK).body(employee);
        } catch (Exception e) {
            return status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageHelperService.getMessage(SERVER_INTERNAL_ERROR));
        }
    }

    @PutMapping()
    public ResponseEntity updateEmployee(@RequestBody Employee employee) {
        logger.debug(LOGGER_SERVICE_STATEMENT_1006, employee.toString());
        try {
            employee = empService.updateEmp(employee);
            return status(HttpStatus.OK).body(employee);
        } catch (Exception e) {
            return status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageHelperService.getMessage(SERVER_INTERNAL_ERROR));
        }
    }
}
