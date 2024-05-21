package com.gft.managementservice.db.entity;

import java.time.LocalDate;
import java.util.List;

import com.gft.managementservice.db.enums.EmployeeType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    private Integer id;
    private String name;

    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;
    private LocalDate dob;
}
