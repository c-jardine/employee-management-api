package com.keplux.employee_management.controller;

import com.keplux.employee_management.domain.Employee;
import com.keplux.employee_management.service.EmployeeService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping("/employees")
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
        Employee savedEmployee = service.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
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
