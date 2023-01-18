package com.keplux.employee_management.controller;

import com.keplux.employee_management.domain.EmployeeDetails;
import com.keplux.employee_management.service.EmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeDetailsController {

    @Autowired
    private EmployeeDetailsService service;

    @PostMapping("/employees/{employeeId}/details")
    public ResponseEntity<EmployeeDetails> save(@PathVariable Long employeeId,
        @RequestBody EmployeeDetails employeeDetails) {
        service.save(employeeId, employeeDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeDetails);
    }
}
