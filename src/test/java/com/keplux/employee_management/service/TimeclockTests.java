package com.keplux.employee_management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.keplux.employee_management.domain.Employee;
import com.keplux.employee_management.domain.Timeclock;
import com.keplux.employee_management.repository.TimeclockRepository;
import java.util.Calendar;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TimeclockTests {

    @Mock
    private TimeclockRepository timeclockRepository;
    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private TimeclockService timeclockService;

    private Timeclock timeclock;
    private Employee employee;

    @BeforeEach
    void setup() {
        employee = Employee.builder()
            .id(1L)
            .firstName("Test")
            .lastName("User")
            .build();
        timeclock = Timeclock.builder()
            .id(1L)
            .employee(employee)
            .build();
    }

    // Save with no current shift.
    @Test
    void whenTimeclockIsEmpty_thenReturnTimeclockWithTimeIn() {
        // Arrange
        when(employeeService.getById(any(Long.class))).thenReturn(employee);
        when(timeclockRepository.save(any(Timeclock.class))).thenReturn(
            timeclock);
        // Act
        Calendar timeIn = Calendar.getInstance();
        timeIn.set(2023, Calendar.JANUARY, 3, 8, 0, 0);
        Timeclock punchedTimeclock = timeclockService.punchTime(1L,
            timeIn.getTime());
        // Assert
        assertEquals(timeIn.getTime(), punchedTimeclock.getTimeIn());
        assertNull(punchedTimeclock.getTimeOut());
        verify(timeclockRepository, times(1)).save(punchedTimeclock);
    }

    // Save with a current shift.
    @Test
    void whenTimeclockHasTimeIn_thenReturnTimeclockWithTimeInAndTimeOut() {
        // Arrange
        when(employeeService.getById(any(Long.class))).thenReturn(employee);
        Calendar timeIn = Calendar.getInstance();
        timeIn.set(2023, Calendar.JANUARY, 3, 8, 0, 0);
        timeclock.setTimeIn(timeIn.getTime());
        employee.setCurrentShift(timeclock);
        when(timeclockRepository.save(any(Timeclock.class))).thenReturn(
            timeclock);
        Calendar timeOut = Calendar.getInstance();
        timeOut.set(2023, Calendar.JANUARY, 3, 16, 0, 0);
        //Act
        Timeclock punchedTimeclock = timeclockService.punchTime(1L,
            timeOut.getTime());
        // Assert
        assertEquals(timeIn.getTime(), punchedTimeclock.getTimeIn());
        assertEquals(timeOut.getTime(), punchedTimeclock.getTimeOut());
        verify(timeclockRepository, times(1)).save(punchedTimeclock);
    }

    // Get all.
    @Test
    void whenTimeclockHasMultipleEntries_thenReturnListOfTimeclockObjects() {
        // Arrange
        Calendar timeIn = Calendar.getInstance();
        timeIn.set(2023, Calendar.JANUARY, 4, 8, 0, 0);
        Calendar timeOut = Calendar.getInstance();
        timeOut.set(2023, Calendar.JANUARY, 4, 16, 0, 0);
        Timeclock timeclock2 = Timeclock.builder()
            .timeIn(timeIn.getTime())
            .timeOut(timeOut.getTime())
            .employee(employee)
            .build();
        when(timeclockRepository.findAll()).thenReturn(
            List.of(timeclock, timeclock2));
        // Act
        List<Timeclock> timeclockData = timeclockService.getAll();
        // Assert
        assertEquals(2, timeclockData.size());
        verify(timeclockRepository, times(1)).findAll();
    }

    // Get by employee id.
    @Test
    void whenTimeclockHasMultipleEntries_thenReturnListOfEmployeeTimeclockObjects() {
        // Arrange
        Calendar timeIn = Calendar.getInstance();
        timeIn.set(2023, Calendar.JANUARY, 4, 8, 0, 0);
        Calendar timeOut = Calendar.getInstance();
        timeOut.set(2023, Calendar.JANUARY, 4, 16, 0, 0);
        Timeclock timeclock2 = Timeclock.builder()
            .timeIn(timeIn.getTime())
            .timeOut(timeOut.getTime())
            .employee(employee)
            .build();
        Employee employee2 = Employee.builder()
            .id(2L)
            .firstName("Steve")
            .build();
        when(employeeService.getById(any(Long.class))).thenReturn(employee);
        when(timeclockRepository.findByEmployeeId(any(Long.class))).thenReturn(
            List.of(timeclock, timeclock2));
        // Act
        List<Timeclock> employee1Timeclock = timeclockService.getAllByEmployeeId(
            1L);
        // Assert
        assertEquals(2, employee1Timeclock.size());
        verify(timeclockRepository, times(1)).findByEmployeeId(1L);
    }
}
