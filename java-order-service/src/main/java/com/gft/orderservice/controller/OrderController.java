package com.gft.orderservice.controller;

import com.gft.orderservice.service.OrderDataService;
import com.gft.orderservice.utils.Constants;

import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/order-service")
public class OrderController {

    private final OrderDataService orderDataService;

    public OrderController(OrderDataService orderDataService) {
        this.orderDataService = orderDataService;
    }

    @GetMapping("/orders")
    public ResponseEntity<Object> getCustomers() {
        return new ResponseEntity<>(orderDataService.getOrders(), HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable("id") Integer id) {
        try {
            MDC.put(Constants.ORDER_ID_MDC_KEY, Integer.toString(id));
            return new ResponseEntity<>(orderDataService.getOrderById(id), HttpStatus.OK);
        } finally {
            MDC.remove(Constants.ORDER_ID_MDC_KEY);
        }
    }
}
