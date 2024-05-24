package com.gft.orderservice.service.impl;

import com.gft.orderservice.configuration.PropertiesConfiguration;
import com.gft.orderservice.db.entity.Order;
import com.gft.orderservice.db.repository.OrderRepository;
import com.gft.orderservice.dto.OrderDetailDto;
import com.gft.orderservice.dto.OrderDto;
import com.gft.orderservice.dto.ProductDto;
import com.gft.orderservice.service.OrderDataService;
import com.gft.orderservice.utils.CollectionUtil;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderDataServiceImpl implements OrderDataService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;
    private final PropertiesConfiguration propertiesConfiguration;

    public OrderDataServiceImpl(OrderRepository orderRepository, RestTemplate restTemplate, PropertiesConfiguration propertiesConfiguration) {
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
            if (!CollectionUtil.isNullOrEmpty(dto.getOrderDetails()))
            {
                for (OrderDetailDto orderDetail : dto.getOrderDetails()) {
                    String url = String.format("%s/v1/product-service/products/%d", this.propertiesConfiguration.getProductServiceUrl(), orderDetail.getProductId());
                    log.info("productUrl: {}", url);
                    ProductDto product = restTemplate.getForObject(url, ProductDto.class);
                    if (product != null) {
                        orderDetail.setProductName(product.getName());
                        orderDetail.setProductPrice(product.getPrice());
                    }
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
            return Optional.empty();
        }
        OrderDto dto = this.modelMapper.map(entity, OrderDto.class);
        log.info("found a record with id: {}", id);
        log.info("getOrderById -> Leave");
        return Optional.of(dto);
    }

}
