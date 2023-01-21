package com.keplux.employee_management.controller;

import com.keplux.employee_management.domain.Timeclock;
import com.keplux.employee_management.service.TimeclockService;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that handles API requests related to the timeclock.
 *
 * @author Chris Jardine
 * @version 0.0
 */
@RestController
@AllArgsConstructor
public class TimeclockController {

    @Autowired
    private TimeclockService service;

    /**
     * If the employee doesn't have a current shift, it clocks them out.
     * Otherwise, it clocks them in.
     *
     * @param id   The employee's id.
     * @param date The date and time for clocking in or out. In the format
     *             <code>"yyyy-MM-dd_HH:mm:ss"</code>.
     * @return The timeclock data.
     */
    @PostMapping("/employees/{id}/timeclock/{date}")
    public ResponseEntity<Timeclock> punchClock(@PathVariable Long id,
        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd_HH:mm:ss") Date date) {
        Timeclock savedTimeclock = service.punchTime(id, date);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTimeclock);
    }

    /**
     * Get all timeclock data.
     * TODO: Implement pagination.
     *
     * @return A list of all timeclock data.
     */
    @GetMapping("/timeclock")
    public ResponseEntity<List<Timeclock>> getAll() {
        List<Timeclock> timeclock = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(timeclock);
    }

    /**
     * Get all timeclock data for an employee.
     * TODO: Implement pagination.
     *
     * @param id The employee's id.
     * @return A list of the employee's timeclock data.
     */
    @GetMapping("/employees/{id}/timeclock")
    public ResponseEntity<List<Timeclock>> getById(@PathVariable Long id) {
        List<Timeclock> timeclock = service.getAllByEmployeeId(id);
        return ResponseEntity.status(HttpStatus.OK).body(timeclock);
    }
}
