package com.keplux.employee_management.service;

import com.keplux.employee_management.domain.Employee;
import com.keplux.employee_management.domain.Timeclock;
import com.keplux.employee_management.repository.EmployeeRepository;
import com.keplux.employee_management.repository.TimeclockRepository;
import java.util.Calendar;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TimeclockTests {

    @Mock
    private TimeclockRepository timeclockRepository;

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
        Calendar timeIn = Calendar.getInstance();
        timeIn.set(2023, Calendar.JANUARY, 3, 8, 0, 0);
        Calendar timeOut = Calendar.getInstance();
        timeIn.set(2023, Calendar.JANUARY, 3, 16, 0, 0);
        timeclock = Timeclock.builder()
            .id(1L)
            .employee(employee)
            .build();
    }

    @Test
    void givenEmptyTimeclock_whenPunchTime_thenReturnTimeclock() {
        // Given
        given(spy(timeclockService).findEmployee(1L)).willReturn(employee);
        given(timeclockRepository.save(timeclock)).willReturn(timeclock);
        // When
        Calendar timeIn = Calendar.getInstance();
        timeIn.set(2023, Calendar.JANUARY, 3, 8, 0, 0);
        Timeclock clockedIn = timeclockService.punchTime(1L, timeIn.getTime());
        //Then
        verify(timeclockRepository, times(1)).save(clockedIn);
    }
}
