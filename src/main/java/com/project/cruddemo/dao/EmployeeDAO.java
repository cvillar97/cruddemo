package com.project.cruddemo.dao;

import com.project.cruddemo.models.Employee;

import java.util.List;

public interface EmployeeDAO {
    public List<Employee> findAll();

    public Employee getEmployee(int id);

    public Employee saveEmployee(Employee employee);

    public void deleteEmployee(int id);
}
