package com.keplux.employee_management.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing an employee.
 *
 * @author Chris Jardine
 * @version 0.0
 */
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

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_details_id", nullable = false)
    @Getter
    @Setter
    private EmployeeDetails employeeDetails;

    @OneToOne
    @JoinColumn(name = "current_shift", referencedColumnName = "id")
    @Getter
    @Setter
    private Timeclock currentShift;

    @OneToMany(mappedBy = "employee")
    @Getter
    @Setter
    private Set<Timeclock> timeclock;
}
