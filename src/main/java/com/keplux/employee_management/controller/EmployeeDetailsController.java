package com.keplux.employee_management.controller;

import com.keplux.employee_management.domain.EmployeeDetails;
import com.keplux.employee_management.service.EmployeeDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that handles API requests related to employee details.
 * @author Chris Jardine
 * @version 0.0
 */
@RestController
@AllArgsConstructor
public class EmployeeDetailsController {

    @Autowired
    private EmployeeDetailsService service;

    /**
     * Update an employee's details.
     *
     * @param employeeId      The employee's id.
     * @param employeeDetails The new employee details.
     * @return The updated object if successful, otherwise
     */
    @PatchMapping("/employees/{employeeId}/details")
    public ResponseEntity<Object> update(@PathVariable Long employeeId,
        @RequestBody EmployeeDetails employeeDetails) {
        EmployeeDetails savedDetails = service.update(employeeId,
            employeeDetails);
        return ResponseEntity.status(HttpStatus.OK).body(savedDetails);
    }
}
