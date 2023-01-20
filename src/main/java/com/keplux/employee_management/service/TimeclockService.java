package com.keplux.employee_management.service;

import com.keplux.employee_management.domain.Employee;
import com.keplux.employee_management.domain.Timeclock;
import com.keplux.employee_management.repository.EmployeeRepository;
import com.keplux.employee_management.repository.TimeclockRepository;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TimeclockService {

  @Autowired
  private TimeclockRepository repository;

  @Autowired
  private EmployeeRepository employeeRepository;

  /**
   * Save a new timeclock entry by employee id.
   *
   * @param employeeId The employee id.
   * @param timeclock  The timeclock data.
   * @return The timeclock data if successful, otherwise throws
   * IllegalArgumentException.
   */
  public Timeclock save(Long employeeId, Timeclock timeclock) {
    Employee employee = employeeRepository.findById(employeeId).orElseThrow(
        () -> new IllegalArgumentException(
            "Employee not found with id:" + employeeId));
    timeclock.setEmployee(employee);
    repository.save(timeclock);
    return timeclock;
  }

  public List<Timeclock> getAll() {
    return repository.findAll();
  }

  public List<Timeclock> getAllByEmployee(Long employeeId) {
    Employee employee = employeeRepository.findById(employeeId).orElseThrow(
        () -> new IllegalArgumentException(
            "Employee not found with id:" + employeeId));
    return repository.findByEmployeeId(employee.getId());
  }

  public List<Timeclock> getAllBetweenTimeIn(Date startDate, Date endDate) {
    return repository.findByTimeInBetween(startDate, endDate);
  }
}
