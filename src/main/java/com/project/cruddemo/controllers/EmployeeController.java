package com.project.cruddemo.controllers;

import com.project.cruddemo.exceptions.BadRequestException;
import com.project.cruddemo.exceptions.EmployeeNotFoundException;
import com.project.cruddemo.models.Employee;
import com.project.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController (EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable String id) {

        int employeeId = 0;
        try {
            employeeId = Integer.parseInt(id);
        } catch (Exception ex) {
            throw new BadRequestException("Failed to convert value of type String to " +
                    "required type int ; For input String: " + id);
        }

        Employee theEmployee = employeeService.getEmployee(employeeId);

        if( theEmployee == null) {
            throw new EmployeeNotFoundException("Employee id not found - " + id);
        }

        return theEmployee;
    }



}
