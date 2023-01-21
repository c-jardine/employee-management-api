package com.keplux.employee_management.controller;

import com.keplux.employee_management.domain.Employee;
import com.keplux.employee_management.service.EmployeeService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to handle API requests related to employees.
 *
 * @author Chris Jardine
 * @version 0.0
 */
@RestController
@AllArgsConstructor
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    /**
     * Save a new employee.
     *
     * @param employee The employee data to be saved.
     * @return The employee data.
     */
    @PostMapping("/employees")
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
        Employee savedEmployee = service.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    /**
     * Get all employees.
     * TODO: Implement pagination.
     *
     * @return A list of all employees.
     */
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> employees = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    /**
     * Get an employee by id.
     *
     * @param id The id of the employee.
     * @return The employee data.
     */
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        Employee employee = service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    /**
     * Delete an employee by id.
     *
     * @param id The id of the employee.
     * @return The employee that was deleted.
     */
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteById(@PathVariable Long id) {
        Employee deletedEmployee = service.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedEmployee);
    }
}
