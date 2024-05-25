package com.gft.orderservice.service.impl;

import com.gft.orderservice.configuration.PropertiesConfiguration;
import com.gft.orderservice.db.entity.Order;
import com.gft.orderservice.db.repository.OrderRepository;
import com.gft.orderservice.dto.OrderDetailDto;
import com.gft.orderservice.dto.OrderDto;
import com.gft.orderservice.dto.ProductDto;
import com.gft.orderservice.service.OrderDataService;
import com.gft.orderservice.utils.CollectionUtil;
import com.gft.orderservice.utils.Constants;
import com.gft.orderservice.utils.RequestContext;

import org.modelmapper.ModelMapper;
import org.slf4j.MDC;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class OrderDataServiceImpl implements OrderDataService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;
    private final PropertiesConfiguration propertiesConfiguration;

    public OrderDataServiceImpl(OrderRepository orderRepository, RestTemplate restTemplate,
            PropertiesConfiguration propertiesConfiguration) {
        this.orderRepository = orderRepository;
        this.modelMapper = new ModelMapper();
        this.restTemplate = restTemplate;
        this.propertiesConfiguration = propertiesConfiguration;
    }

    @Override
    public List<OrderDto> getOrders() {
        log.info("getOrders <- Enter");
        List<OrderDto> dtoList = new ArrayList<>();
        List<Order> entities = orderRepository.findAll();
        if (CollectionUtil.isNullOrEmpty(entities)) {
            log.info("getOrders - no records found -> Leave");
            return dtoList;
        }
        log.info("found {} records", entities.size());
        for (Order entity : entities) {
            OrderDto dto = this.modelMapper.map(entity, OrderDto.class);
            if (!CollectionUtil.isNullOrEmpty(dto.getOrderDetails())) {
                for (OrderDetailDto orderDetail : dto.getOrderDetails()) {
                    fetchProductData(orderDetail);
                }
            }
            dtoList.add(dto);
        }
        log.info("getOrders -> Leave");
        return dtoList;
    }

    @Override
    public Optional<OrderDto> getOrderById(Integer id) {
        log.info("getOrderById - id: {} <- Enter", id);
        Optional<Order> entity = orderRepository.findById(id);
        if (entity.isEmpty()) {
            log.info("getOrderById - not found record with id: {} -> Leave", id);
            throw new NoSuchElementException(String.format("not found record with id: %d", id));
        }
        log.info("found a record with id: {}", id);
        OrderDto dto = this.modelMapper.map(entity, OrderDto.class);
        if (!CollectionUtil.isNullOrEmpty(dto.getOrderDetails())) {
            for (OrderDetailDto orderDetail : dto.getOrderDetails()) {
                fetchProductData(orderDetail);
            }
        }
        log.info("getOrderById -> Leave");
        return Optional.of(dto);
    }

    private void fetchProductData(OrderDetailDto orderDetail) {
        String url = String.format("%s/v1/product-service/products/%d",
                this.propertiesConfiguration.getProductServiceUrl(), orderDetail.getProductId());
        log.info("productUrl: {}", url);

        HttpHeaders headers = new HttpHeaders();
        headers.set(Constants.CUSTOMER_ID_MDC_KEY, RequestContext.getCustomerId());

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<ProductDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, ProductDto.class);
        if (response != null) {
            ProductDto product = response.getBody();
            if (product != null) {
                orderDetail.setProductName(product.getName());
                orderDetail.setProductPrice(product.getPrice());
            }
        }
    }

}
