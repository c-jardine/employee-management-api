package com.keplux.employee_management.repository;

import com.keplux.employee_management.domain.Timeclock;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeclockRepository extends JpaRepository<Timeclock, Long> {
    List<Timeclock> findByEmployeeId(Long employeeId);

    List<Timeclock> findByTimeInBetween(Date startDate, Date endDate);
}
