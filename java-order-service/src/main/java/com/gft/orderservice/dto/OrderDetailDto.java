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
public class OrderDetailDto {
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private String productName;
    private Float productPrice;
    private Integer quantity;
}
