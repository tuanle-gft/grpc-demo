package com.gft.managementservice.db.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Certificate {
    @Id
    private Integer id;

    @Column(name = "employee_id", updatable = false, insertable = false)
    private Integer employeeId;
    private String name;
    private String grade;
    private Float score;
    
    @Temporal(TemporalType.DATE)
    private Date expiredDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
