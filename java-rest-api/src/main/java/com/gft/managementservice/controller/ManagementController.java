package com.gft.managementservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gft.managementservice.service.ManagementDataService;

@RestController
@RequestMapping("/v1/management-service")
public class ManagementController {
    private final ManagementDataService managementDataService;

    public ManagementController(ManagementDataService managementDataService) {
        this.managementDataService = managementDataService;
    }
    
    @GetMapping("/employees")
    public ResponseEntity<Object> getEmployees() {
        return new ResponseEntity<>(managementDataService.getEmployees(), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(managementDataService.getEmployeeById(id), HttpStatus.OK);
    }
}
