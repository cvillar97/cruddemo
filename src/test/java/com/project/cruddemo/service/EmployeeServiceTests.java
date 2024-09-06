package com.project.cruddemo.service;

import com.project.cruddemo.exceptions.EmployeeNotFoundException;
import com.project.cruddemo.models.Employee;
import com.project.cruddemo.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl underTest;

    @Test
    public void EmployeeService_getAll_CallsProperMethod() {

        underTest.getAll();

        verify(employeeRepository).findAll();
    }

    @Test
    public void EmployeeService_GetEmployeeById_PassesSameArgument() {

        Employee employee = Employee.builder()
                .id(0)
                .firstName("Carlos")
                .lastName("Nike")
                .email("nike.carlos@gmail.com")
                .build();

        Optional<Employee> employeeOpt = Optional.of(employee);

        when(employeeRepository.findById(0)).thenReturn(employeeOpt);

        ArgumentCaptor<Integer> integerArgumentCaptor =
                ArgumentCaptor.forClass(Integer.class);

        underTest.getEmployee(0);

        verify(employeeRepository).findById(integerArgumentCaptor.capture());

        int capturedId = integerArgumentCaptor.getValue();

        assertThat(capturedId).isEqualTo(0);
    }

    @Test
    public void EmployeeService_Save_CanSaveEmployee() {
        Employee employee = Employee.builder()
                .firstName("Juan")
                .lastName("Alonso")
                .email("alonso.juan@gmail.com")
                .build();

        underTest.saveEmployee(employee);

        ArgumentCaptor <Employee> employeeArgumentCaptor =
                ArgumentCaptor.forClass(Employee.class);

        verify(employeeRepository).save(employeeArgumentCaptor.capture());

        Employee capturedEmployee = employeeArgumentCaptor.getValue();

        assertThat(capturedEmployee).isEqualTo(employee);
    }

    @Test
    public void EmployeeService_DeleteEmployee_CallsDeleteById() {

        Employee employee = Employee.builder()
                .firstName("Juan")
                .lastName("Alonso")
                .email("alonso.juan@gmail.com")
                .build();

        employeeRepository.save(employee);

        given(employeeRepository.existsById(0)).willReturn(true);

        underTest.deleteEmployee(0);

        verify(employeeRepository).deleteById(0);
    }

