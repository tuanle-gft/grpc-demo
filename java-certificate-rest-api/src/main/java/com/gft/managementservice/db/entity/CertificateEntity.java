package com.gft.managementservice.db.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "certificate")
public class CertificateEntity {
    @Id
    private Integer id;
    private Integer employeeId;
    private String name;
    private String grade;
    private Float score;
    private LocalDate expiredDate;
}
