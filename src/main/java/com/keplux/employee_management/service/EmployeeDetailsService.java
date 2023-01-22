package com.keplux.employee_management.service;

import com.keplux.employee_management.domain.Employee;
import com.keplux.employee_management.domain.EmployeeDetails;
import com.keplux.employee_management.repository.EmployeeDetailsRepository;
import com.keplux.employee_management.repository.EmployeeRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer that handles access to the database for employee details.
 *
 * @author Chris Jardine
 * @version 0.0
 */
@Service
@AllArgsConstructor
public class EmployeeDetailsService {

    @Autowired
    private EmployeeDetailsRepository repository;
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Updates an employee's details.
     *
     * @param employeeId         The employee's id.
     * @param newEmployeeDetails The updated employee details.
     * @return The updated employee details if successful, otherwise throws
     * IllegalArgumentException.
     */
    @Transactional
    public EmployeeDetails update(Long employeeId,
        EmployeeDetails newEmployeeDetails) {
        // Get the employee and existing details
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new IllegalArgumentException(
                "Employee not found with id:" + employeeId));
        EmployeeDetails updatedDetails = employee.getEmployeeDetails();
        // Update with new details while preserving the old.
        // TODO: Figure out how to mock the ObjectMapper.updateValue method.
        if (newEmployeeDetails.getEmail() != null) {
            updatedDetails.setEmail(
                newEmployeeDetails.getEmail());
        }
        if (newEmployeeDetails.getPhoneNumber() != null) {
            updatedDetails.setPhoneNumber(
                newEmployeeDetails.getPhoneNumber());
        }
        if (newEmployeeDetails.getStartDate() != null) {
            updatedDetails.setStartDate(
                newEmployeeDetails.getStartDate());
        }
        if (newEmployeeDetails.getPayRate() != null) {
            updatedDetails.setPayRate(
                newEmployeeDetails.getPayRate());
        }
        if (newEmployeeDetails.getEmployee() != null) {
            updatedDetails.setEmployee(
                newEmployeeDetails.getEmployee());
        }
        repository.save(updatedDetails);
        return updatedDetails;
    }
}
