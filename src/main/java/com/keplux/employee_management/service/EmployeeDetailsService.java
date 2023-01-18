package com.keplux.employee_management.service;

import com.keplux.employee_management.domain.Employee;
import com.keplux.employee_management.domain.EmployeeDetails;
import com.keplux.employee_management.repository.EmployeeDetailsRepository;
import com.keplux.employee_management.repository.EmployeeRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDetailsService {

    @Autowired
    private EmployeeDetailsRepository repository;
    @Autowired
    private EmployeeRepository employeeRepository;

    private EmployeeDetails updateDetails(EmployeeDetails oldDetails, EmployeeDetails newDetails) {
        newDetails.setId(oldDetails.getId());
        return newDetails;
    }

    public void save(Long employeeId, EmployeeDetails employeeDetails) {
        Optional<Employee> foundEmployee = employeeRepository.findById(employeeId);
        if (foundEmployee.isPresent()) {
            Employee employee = foundEmployee.get();
            EmployeeDetails oldEmployeeDetails = employee.getEmployeeDetails();
            // If the employee has existing details, update them instead.
            if (oldEmployeeDetails != null) {
                EmployeeDetails updatedDetails = updateDetails(oldEmployeeDetails, employeeDetails);
                updatedDetails.setEmployee(employee);
                repository.save(updatedDetails);
            } else {
                employeeDetails.setEmployee(employee);
                repository.save(employeeDetails);
                employee.setEmployeeDetails(employeeDetails);
                employeeRepository.save(employee);
            }
        } else {
            throw new IllegalArgumentException("Unable to find user with id: " + employeeId);
        }
    }
}
