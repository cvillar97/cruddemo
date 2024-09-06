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

    @Test
    public void EmployeeRepository_FindAll_ReturnsMoreThanOneEmployee() {
        Employee employee1 = Employee.builder()
                .firstName("Juan")
                .lastName("Villegas").build();
        Employee employee2 = Employee.builder()
                .firstName("Roca")
                .lastName("Johnson").build();

        Employee saved1 = underTest.save(employee1);
        Employee saved2 = underTest.save(employee2);

        List<Employee> employees = underTest.findAll();

        assertThat(employees).isNotNull();
        assertThat(employees.size()).isEqualTo(2);
    }

    @Test
    public void EmployeeRepository_FindById_ReturnsTheSavedEmployeeById() {

        Employee theEmployee = Employee.builder()
                .firstName("Hugo")
                .lastName("Valles")
                .build();

        underTest.save(theEmployee);

        int theEmployeeId = theEmployee.getId();

        Employee foundEmployee = underTest.findById(theEmployeeId).get();

        assertThat(foundEmployee).isNotNull();
        assertThat(foundEmployee.getId()).isEqualTo(theEmployeeId);
    }

    @Test
    public void EmployeeRepository_FindByLastName_ReturnsEmployeeNotNull() {

        Employee theEmployee = Employee.builder()
                .firstName("Hugo")
                .lastName("Valles")
                .build();

        underTest.save(theEmployee);

        String theEmployeeLastName = theEmployee.getLastName();

        Employee foundEmployee = underTest.findByLastName(theEmployeeLastName).get();

        assertThat(foundEmployee).isNotNull();
        assertThat(foundEmployee.getFirstName()).isEqualTo("Hugo");
    }

    @Test
    public void EmployeeRepository_Update_ReturnsUpdatedEmployee() {

        Employee theEmployee = Employee.builder()
                .firstName("Hugo")
                .lastName("Valles")
                .build();

        underTest.save(theEmployee);
        theEmployee.setFirstName("Carlos");
        theEmployee.setLastName("Juarez");

        Employee updatedEmployee = underTest.save(theEmployee);

        assertThat(updatedEmployee).isNotNull();
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Carlos");
        assertThat(updatedEmployee.getLastName()).isEqualTo("Juarez");
    }

    @Test
    public void EmployeeRepository_DeleteById_ReturnsEmployeeIsEmpty() {

        Employee theEmployee = Employee.builder()
                .firstName("Hugo")
                .lastName("Valles")
                .build();

        underTest.save(theEmployee);

        underTest.deleteById(theEmployee.getId());
        Optional<Employee> employeeReturn = underTest.findById(theEmployee.getId());

        assertThat(employeeReturn).isEmpty();
    }
}
