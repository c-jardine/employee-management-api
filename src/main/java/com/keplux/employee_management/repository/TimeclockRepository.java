package com.keplux.employee_management.repository;

import com.keplux.employee_management.domain.Timeclock;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for the timeclock.
 *
 * @author Chris Jardine
 * @version 0.0
 */
public interface TimeclockRepository extends JpaRepository<Timeclock, Long> {

    /**
     * Get timeclock data for an employee.
     *
     * @param employeeId The employee's id.
     * @return A list of the employee's timeclock data.
     */
    List<Timeclock> findByEmployeeId(Long employeeId);

    /**
     * Get timeclock data between two dates.
     *
     * @param startDate The starting date to be searched.
     * @param endDate   The ending date to be searched.
     * @return A list of timeclock data between the provided dates.
     */
    List<Timeclock> findByTimeInBetween(Date startDate, Date endDate);
}
