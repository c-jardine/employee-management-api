package com.keplux.employee_management.service;

import com.keplux.employee_management.domain.Employee;
import com.keplux.employee_management.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {
    @Mock
    private EmployeeRepository repository;
    @InjectMocks
    private EmployeeService service;

    private Employee employee;

    @BeforeEach
    public void setup() {
        employee = Employee.builder().id(1L).firstName("Test").lastName("User").build();
    }

    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {
        when(repository.save(any(Employee.class))).thenReturn(employee);
        service.save(employee);
        verify(repository, times(1)).save(any(Employee.class));
    }

    @Test
    public void givenEmployeeObject_whenGetAllEmployees_thenReturnEmployeesList() {
        when(repository.findAll()).thenReturn(List.of(employee));
        // TODO: update with JUnit assertion
        service.getAll();
        verify(repository, times(1)).findAll();
    }
}
