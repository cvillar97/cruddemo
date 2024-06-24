package com.project.cruddemo.dao;

import com.project.cruddemo.models.Employee;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        String query = "FROM Employee";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Employee getEmployee(int id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {

        Employee dbEmployee = entityManager.merge(employee);

        return dbEmployee;
    }

    @Override
    public void deleteEmployee(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
    }
}
