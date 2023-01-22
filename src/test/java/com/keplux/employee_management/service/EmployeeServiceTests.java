package com.keplux.employee_management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    void whenSaveEmployee_thenReturnEmployee() {
        // Arrange
        when(repository.save(any(Employee.class))).thenReturn(employee);
        // Act
        Employee savedEmployee = service.save(employee);
        // Assert
        assertNotNull(savedEmployee);
        verify(repository, times(1)).save(any(Employee.class));
    }

    @Test
    void whenGetAllEmployees_thenReturnListOfAllEmployees() {
        // Arrange
        Employee employee2 = Employee.builder()
            .id(2L)
            .firstName("Steve")
            .lastName("Jobs")
            .build();
        when(repository.findAll()).thenReturn(List.of(employee, employee2));
        // Act
        List<Employee> savedEmployees = service.getAll();
        // Assert
        assertEquals(2, savedEmployees.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void whenGetByIdWithValidId_thenReturnEmployee() {
        // Arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(employee));
        // Act
        Employee savedEmployee = service.getById(id);
        // Assert
        assertNotNull(savedEmployee);
        assertEquals(employee.toString(), savedEmployee.toString());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void whenGetByIdWithInvalidId_thenThrowException() {
        // Arrange
        Long invalidId = 2L;
        when(repository.findById(invalidId)).thenThrow(
            IllegalArgumentException.class);
        // Act
        assertThrows(IllegalArgumentException.class,
            () -> service.getById(invalidId));
        // Assert
        verify(repository, times(1)).findById(invalidId);
    }

    @Test
    void whenUpdateWithValidFields_thenReturnUpdatedEmployee() {
        // Arrange
        String updatedFirstName = "Updated";
        when(repository.findById(any(Long.class))).thenReturn(
            Optional.of(employee));
        employee.setFirstName(updatedFirstName);
        when(repository.save(any(Employee.class))).thenReturn(employee);
        // Act
        Long id = employee.getId();
        Employee updatedEmployee = service.updateById(id, employee);
        // Assert
        assertEquals(updatedFirstName, updatedEmployee.getFirstName());
        assertEquals(employee.getLastName(), updatedEmployee.getLastName());
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(employee);
    }

    @Test
    void whenDeleteByIdWithValid_thenReturnDeletedEmployee() {
        // Arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(employee));
        doNothing().when(repository).deleteById(id);
        // Act
        service.deleteById(id);
        // Assert
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void whenDeleteByIdWithInvalidId_thenThrowException() {
        // Given
        Long id = 2L;
        when(repository.findById(id)).thenThrow(
            IllegalArgumentException.class);
        //When
        assertThrows(IllegalArgumentException.class,
            () -> service.deleteById(id));
        // Then
        verify(repository, times(0)).deleteById(id);
    }
}
