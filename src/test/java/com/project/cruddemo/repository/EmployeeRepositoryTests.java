package com.project.cruddemo.repository;

import com.project.cruddemo.models.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    public EmployeeRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    public void EmployeeRepository_Save_ReturnsTheSavedEmployee() {

    Employee theEmployee = Employee.builder()
            .firstName("Hugo")
            .lastName("Valles")
            .build();

    Employee savedEmployee = underTest.save(theEmployee);

    assertThat(savedEmployee).isNotNull();
    assertThat(savedEmployee.getId()).isGreaterThan(0);
    assertThat(savedEmployee.getFirstName()).isEqualTo("Hugo");
    assertThat(savedEmployee.getLastName()).isEqualTo("Valles");
    }
