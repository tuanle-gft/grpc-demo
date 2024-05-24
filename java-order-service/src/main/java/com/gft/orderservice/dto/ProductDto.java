package com.gft.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {
    private Integer id;
    private String name;
    private Float price;
}
