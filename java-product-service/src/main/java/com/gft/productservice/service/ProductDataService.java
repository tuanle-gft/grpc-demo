package com.gft.productservice.service;

import java.util.List;
import java.util.Optional;

import com.gft.productservice.dto.ProductDto;

public interface ProductDataService {
    List<ProductDto> getProducts();
    Optional<ProductDto> getProductById(Integer id);
}
