package com.gft.managementservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gft.managementservice.enums.EmployeeType;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDto implements Serializable {
    private Integer id;
    private String name;
    private EmployeeType employeeType;
    private Date dob;
    private List<CertificateDto> certificates;
}
