package com.keplux.employee_management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.keplux.employee_management.domain.Employee;
import com.keplux.employee_management.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTests {

    @Mock
    private EmployeeRepository repository;
    @InjectMocks
    private EmployeeService service;

    private Employee employee;

    @BeforeEach
    void setup() {
        employee = Employee.builder()
            .id(1L)
            .firstName("Test")
            .lastName("User")
            .build();
    }

    @Test
    void givenEmployee_whenSave_thenReturnEmployee() {
        // Given
        given(repository.save(any(Employee.class))).willReturn(employee);
        // When
        Employee savedEmployee = service.save(employee);
        // Then
        assertNotNull(savedEmployee);
        verify(repository, times(1)).save(any(Employee.class));
    }

    @Test
    void givenEmployeeList_whenGetAll_thenReturnEmployees() {
        // Given
        Employee employee2 = Employee.builder()
            .id(2L)
            .firstName("Steve")
            .lastName("Jobs")
            .build();
        given(repository.findAll()).willReturn(List.of(employee, employee2));
        // When
        List<Employee> savedEmployees = service.getAll();
        // Then
        assertEquals(2, savedEmployees.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void givenExistingEmployeeId_whenGetById_thenReturnEmployee() {
        // Given
        Long id = 1L;
        given(repository.findById(id)).willReturn(Optional.of(employee));
        // When
        Employee savedEmployee = service.getById(id);
        // Then
        assertNotNull(savedEmployee);
        assertEquals(employee.toString(), savedEmployee.toString());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void givenInvalidEmployeeId_whenGetById_thenThrowException() {
        Long invalidId = 2L;
        // Given
        given(repository.findById(invalidId)).willThrow(
            IllegalArgumentException.class);
        // When
        assertThrows(IllegalArgumentException.class,
            () -> service.getById(invalidId));
        // Then
        verify(repository, times(1)).findById(invalidId);
    }

    @Test
    void givenExistingEmployeeId_whenDeleteById_thenReturnEmployee() {
        // Given
        Long id = 1L;
        given(repository.findById(id)).willReturn(Optional.of(employee));
        willDoNothing().given(repository).deleteById(id);
        // When
        service.deleteById(id);
        // Then
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void givenInvalidEmployeeId_whenDeleteById_thenThrowException() {
        // Given
        Long id = 2L;
        given(repository.findById(id)).willThrow(
            IllegalArgumentException.class);
        //When
        assertThrows(IllegalArgumentException.class,
            () -> service.deleteById(id));
        // Then
        verify(repository, times(0)).deleteById(id);
    }
}
