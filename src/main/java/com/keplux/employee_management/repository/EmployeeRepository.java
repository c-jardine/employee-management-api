package com.keplux.employee_management.repository;

import com.keplux.employee_management.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for employees.
 *
 * @author Chris Jardine
 * @version 0.0
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
