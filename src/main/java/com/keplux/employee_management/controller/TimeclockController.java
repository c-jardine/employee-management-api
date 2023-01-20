package com.keplux.employee_management.controller;

import com.keplux.employee_management.domain.Timeclock;
import com.keplux.employee_management.service.TimeclockService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TimeclockController {

  @Autowired
  private TimeclockService service;

  @PostMapping("/employees/{id}/timeclock")
  public ResponseEntity<Timeclock> save(@PathVariable Long id,
      @RequestBody Timeclock timeclock) {
    Timeclock savedTimeclock = service.save(id, timeclock);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedTimeclock);
  }

  @GetMapping("/timeclock")
  public ResponseEntity<List<Timeclock>> getAll() {
    List<Timeclock> timeclock = service.getAll();
    return ResponseEntity.status(HttpStatus.OK).body(timeclock);
  }
}
