package com.gft.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {
    private Integer id;
    private String customerName;
    private LocalDate createdDate;
    private List<OrderDetailDto> orderDetails;
}
