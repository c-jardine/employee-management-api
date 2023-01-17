package com.keplux.employee_management.service;

import com.keplux.employee_management.domain.Employee;
import com.keplux.employee_management.repository.EmployeeDetailsRepository;
import com.keplux.employee_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepository;

    public void save(Employee employee) {
        repository.save(employee);
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Employee getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee not found with id:" + id));
    }
}
