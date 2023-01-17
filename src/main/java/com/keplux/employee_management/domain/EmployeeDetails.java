package com.keplux.employee_management.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee_details")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne(mappedBy = "employeeDetails")
    @JsonIgnore
    @Getter
    @Setter
    private Employee employee;
}
