package com.keplux.employee_management.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keplux.employee_management.domain.Employee;
import com.keplux.employee_management.domain.EmployeeDetails;
import com.keplux.employee_management.repository.EmployeeDetailsRepository;
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
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper mapper;

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
        try {
            // Get the employee and existing details
            Employee employee = employeeService.getById(employeeId);
            Long oldDetailsId = employee.getEmployeeDetails().getId();
            EmployeeDetails oldDetails = employee.getEmployeeDetails();
            newEmployeeDetails.setId(oldDetailsId);
            // Update with new details while preserving the old.
            EmployeeDetails updatedDetails = mapper.updateValue(oldDetails,
                newEmployeeDetails);
            repository.save(updatedDetails);
            return updatedDetails;
        } catch (JsonMappingException e) {
            // TODO: Create custom exception.
            throw new IllegalArgumentException("Error updating");
        }
    }
}
