package com.keplux.employee_management.controller;

import com.keplux.employee_management.domain.Employee;
import com.keplux.employee_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @PostMapping("/employees")
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
        service.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> employees = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        Employee employee = service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }
}
