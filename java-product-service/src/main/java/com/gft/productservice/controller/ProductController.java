package com.gft.productservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.productservice.service.ProductDataService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1/product-service")
public class ProductController {

    private final ProductDataService productDataService;

    public ProductController(ProductDataService productDataService) {
        this.productDataService = productDataService;
    }

    @GetMapping("/products")
    public ResponseEntity<Object> getProducts() {
        return new ResponseEntity<>(productDataService.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable("id") Integer id, HttpServletRequest request) {
        return new ResponseEntity<>(productDataService.getProductById(id), HttpStatus.OK);
    }
}
