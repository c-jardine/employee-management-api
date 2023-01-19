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

    public Timeclock save(Timeclock timeclock) {
        repository.save(timeclock);
        return timeclock;
    }

    public List<Timeclock> getAll() {
        return repository.findAll();
    }

    public List<Timeclock> getAllByEmployee(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    public List<Timeclock> getAllBetweenTimeIn(Date startDate, Date endDate) {
        return repository.findByTimeInBetween(startDate, endDate);
    }
}
