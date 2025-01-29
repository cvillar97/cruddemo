package com.project.cruddemo.service;

import com.project.cruddemo.repository.EmployeeRepository;
import com.project.cruddemo.exceptions.EmployeeNotFoundException;
import com.project.cruddemo.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmpRepo) {
        this.employeeRepository = theEmpRepo;
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(int id) {
        Optional<Employee> result = employeeRepository.findById(id);

        Employee theEmployee = null;

        if(result.isPresent()) {
            theEmployee = result.get();
        } else {
            throw new EmployeeNotFoundException("Employee id not found - " + id);
        }

        return theEmployee;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(int id) {

        if(employeeRepository.existsById(id) == false) {
            throw new EmployeeNotFoundException(
                    "Employee with id " + id + " does not exist"
            );
        }
        employeeRepository.deleteById(id);
    }
}
