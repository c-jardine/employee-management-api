package com.keplux.employee_management.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Entity representing an employee's details.
 *
 * @author Chris Jardine
 * @version 0.0
 */
@Entity
@DynamicUpdate
@Table(name = "employee_details")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @Getter
    @Setter
    private Long id;

    @Column(name = "phone_number")
    @Getter
    @Setter
    private String phoneNumber;

    @Column(name = "email")
    @Getter
    @Setter
    private String email;

    @Column(name = "start_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Getter
    @Setter
    private Date startDate;

    @Column(name = "pay_rate")
    @Getter
    @Setter
    private Double payRate;

    @OneToOne(mappedBy = "employeeDetails")
    @JsonIgnore
    @Getter
    @Setter
    private Employee employee;
}
