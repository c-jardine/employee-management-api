package com.keplux.employee_management.service;

import com.keplux.employee_management.domain.Employee;
import com.keplux.employee_management.repository.EmployeeRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            .orElseThrow(() -> new IllegalArgumentException("Employee not found with id:" + id));
    }

    /**
     * Delete employee by id.
     *
     * @param id The id of the employee.
     */
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
