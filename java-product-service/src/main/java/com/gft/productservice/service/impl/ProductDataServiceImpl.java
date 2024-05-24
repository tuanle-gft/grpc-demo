package com.gft.productservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gft.productservice.db.entity.Product;
import com.gft.productservice.db.repository.ProductRepository;
import com.gft.productservice.dto.ProductDto;
import com.gft.productservice.service.ProductDataService;
import com.gft.productservice.utils.CollectionUtil;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductDataServiceImpl implements ProductDataService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductDataServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<ProductDto> getProducts() {
        log.info("getProducts <- Enter");
        List<ProductDto> dtoList = new ArrayList<>();
        List<Product> entities = productRepository.findAll();
        if (CollectionUtil.isNullOrEmpty(entities)) {
            log.info("getProducts - no records found -> Leave");
            return dtoList;
        }
        log.info("found {} records", entities.size());
        for (Product entity : entities) {
            ProductDto dto = this.modelMapper.map(entity, ProductDto.class);
            dtoList.add(dto);
        }
        log.info("getProducts -> Leave");
        return dtoList;
    }

    @Override
    public Optional<ProductDto> getProductById(Integer id) {
        log.info("getProductById - id: {} <- Enter", id);
        Optional<Product> entity = productRepository.findById(id);
        if (entity.isEmpty()) {
            log.info("getProductById - not found record with id: {} -> Leave", id);
            return Optional.empty();
        }
        ProductDto dto = this.modelMapper.map(entity, ProductDto.class);
        log.info("found a record with id: {}", id);
        log.info("getProductById -> Leave");
        return Optional.of(dto);
    }

}
