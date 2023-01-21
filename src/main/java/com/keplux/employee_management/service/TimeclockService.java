package com.keplux.employee_management.service;

import com.keplux.employee_management.domain.Employee;
import com.keplux.employee_management.domain.Timeclock;
import com.keplux.employee_management.repository.TimeclockRepository;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer that handles access to the database for the timeclock.
 *
 * @author Chris Jardine
 * @version 0.0
 */
@Service
@AllArgsConstructor
public class TimeclockService {

    @Autowired
    private TimeclockRepository repository;

    @Autowired
    private EmployeeService employeeService;

    /**
     * Clocks an employee in or out, depending on the status of their current
     * shift.
     *
     * @param employeeId The employee id.
     * @param date       The date used to clock in or out.
     * @return The timeclock data if successful, otherwise throws
     * IllegalArgumentException.
     */
    @Transactional
    public Timeclock punchTime(Long employeeId, Date date) {
        // Find the employee with the given id or throw an exception.
        Employee employee = employeeService.getById(employeeId);
        Timeclock timeclock;
        if (employee.getCurrentShift() != null) {
            // Clock out
            timeclock = employee.getCurrentShift();
            timeclock.setTimeOut(date);
            employee.setCurrentShift(null);
        } else {
            // Clock in
            timeclock = Timeclock.builder()
                .timeIn(date)
                .employee(employee)
                .build();
            employee.setCurrentShift(timeclock);
        }
        // Save/update the data
        repository.save(timeclock);
        employeeService.updateById(employeeId, employee);
        return timeclock;
    }

    /**
     * Get all timeclock data.
     *
     * @return A list of the timeclock data.
     */
    public List<Timeclock> getAll() {
        return repository.findAll();
    }

    /**
     * Get all timeclock data belonging to an employee.
     *
     * @param employeeId The employee id.
     * @return A list of timeclock data belonging to the employee.
     */
    public List<Timeclock> getAllByEmployeeId(Long employeeId) {
        Employee employee = employeeService.getById(employeeId);
        return repository.findByEmployeeId(employee.getId());
    }

    /**
     * Get all timeclock data between two dates.
     *
     * @param startDate The start date.
     * @param endDate   The end date.
     * @return A list of timeclock data between the two dates.
     */
    public List<Timeclock> getAllBetweenTimeIn(Date startDate, Date endDate) {
        return repository.findByTimeInBetween(startDate, endDate);
    }
}
