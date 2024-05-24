package com.gft.orderservice.service;

import com.gft.orderservice.dto.OrderDto;

import java.util.List;
import java.util.Optional;

public interface OrderDataService {
    List<OrderDto> getOrders();
    Optional<OrderDto> getOrderById(Integer id);
}
