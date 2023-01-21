package com.keplux.employee_management.repository;

import com.keplux.employee_management.domain.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for employee details.
 *
 * @author Chris Jardine
 * @version 0.0
 */
@Repository
public interface EmployeeDetailsRepository extends
    JpaRepository<EmployeeDetails, Long> {

}
