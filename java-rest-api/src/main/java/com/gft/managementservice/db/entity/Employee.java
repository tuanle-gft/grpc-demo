package com.gft.managementservice.db.entity;

import java.util.Date;
import java.util.List;

import com.gft.managementservice.enums.EmployeeType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    private Integer id;
    private String name;

    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    @Temporal(TemporalType.DATE)
    private Date dob;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Certificate> certificates;
}
