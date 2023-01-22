package com.keplux.employee_management.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.keplux.employee_management.domain.Employee;
import com.keplux.employee_management.domain.EmployeeDetails;
import com.keplux.employee_management.repository.EmployeeDetailsRepository;
import com.keplux.employee_management.repository.EmployeeRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeDetailsTests {

    @Mock
    private EmployeeDetailsRepository detailsRepository;
    @InjectMocks
    private EmployeeDetailsService detailsService;

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeDetails employeeDetails;
    private Employee employee;

    @BeforeEach
    void setup() {
        employee = Employee.builder()
            .id(1L)
            .firstName("Test")
            .lastName("User")
            .build();
        employeeDetails = EmployeeDetails.builder()
            .id(1L)
            .email("test@user.com")
            .phoneNumber("5551237890")
            .employee(employee)
            .build();
        employee.setEmployeeDetails(employeeDetails);
    }

    @Test
    void whenEmployeeDetailsUpdated_thenReturnUpdatedEmployeeDetails() {
        // Arrange
        String newEmail = "user@test.com";
        String newPhoneNumber = "1235550987";
        EmployeeDetails newDetails = EmployeeDetails.builder()
            .id(1L)
            .email(newEmail)
            .phoneNumber(newPhoneNumber)
            .build();
        when(employeeRepository.findById(any(Long.class))).thenReturn(
            Optional.of(employee));
        when(detailsRepository.save(any(EmployeeDetails.class))).thenReturn(
            newDetails);
        // Act
        EmployeeDetails updatedDetails = detailsService.update(1L,
            newDetails);
        // Assert
        assertTrue(updatedDetails.getEmail().contentEquals(newEmail));
        assertTrue(
            updatedDetails.getPhoneNumber().contentEquals(newPhoneNumber));
        verify(detailsRepository, times(1)).save(any(EmployeeDetails.class));
    }
}
