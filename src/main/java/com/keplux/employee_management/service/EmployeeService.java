package com.keplux.employee_management.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keplux.employee_management.domain.Employee;
import com.keplux.employee_management.domain.EmployeeDetails;
import com.keplux.employee_management.repository.EmployeeRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer that handles access to the database for employees.
 *
 * @author Chris Jardine
 * @version 0.0
 */
@Service
@AllArgsConstructor
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    /**
     * Saves the employee.
     *
     * @param employee The employee to be saved.
     * @return The saved employee.
     */
    public Employee save(Employee employee) {
        EmployeeDetails details = EmployeeDetails.builder()
            .employee(employee)
            .build();
        employee.setEmployeeDetails(details);
        repository.save(employee);
        return employee;
    }

    /**
     * Get all employees.
     *
     * @return A list of all employees.
     */
    public List<Employee> getAll() {
        return repository.findAll();
    }

    /**
     * Get an employee by id.
     *
     * @param id The id belonging to the employee.
     * @return The employee if found, otherwise throws IllegalArgumentException.
     */
    public Employee getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(
                "Employee not found with id:" + id));
    }

    /**
     * Delete employee by id.
     *
     * @param id The id of the employee.
     * @return The employee if found and deleted, otherwise throws
     * IllegalArgumentException.
     */
    public Employee deleteById(Long id) {
        Employee employee = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(
                "Employee not found with id:" + id));
        repository.deleteById(id);
        return employee;
    }

    /**
     * Update an employee by id.
     *
     * @param id      The id of the employee.
     * @param newData The new employee data.
     * @return The updated employee data if successful, otherwise throws
     * IllegalArgumentException.
     */
    @Transactional
    public Employee updateById(Long id, Employee newData) {
        Employee employee = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(
                "Employee not found with id:" + id));
        if (employee.getFirstName() != null) {
            employee.setFirstName(employee.getFirstName());
        }
        if (employee.getLastName() != null) {
            employee.setLastName(employee.getLastName());
        }
        if (employee.getEmployeeDetails() != null) {
            employee.setEmployeeDetails(employee.getEmployeeDetails());
        }
        if (employee.getCurrentShift() != null) {
            employee.setCurrentShift(employee.getCurrentShift());
        }
        if (employee.getTimeclock() != null) {
            employee.setTimeclock(employee.getTimeclock());
        }
        repository.save(employee);
        return employee;
    }
}
