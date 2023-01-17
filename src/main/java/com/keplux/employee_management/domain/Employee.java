package com.keplux.employee_management.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name = "first_name", nullable = false)
    @Getter
    @Setter
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Getter
    @Setter
    private String lastName;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "employee_details_id")
    @Getter
    @Setter
    private EmployeeDetails employeeDetails;
}
