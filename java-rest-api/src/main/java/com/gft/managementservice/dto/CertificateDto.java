package com.gft.managementservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CertificateDto implements Serializable {
    private Integer id;
    private String name;
    private String grade;
    private Float score;
    private Date expiredDate;
}
