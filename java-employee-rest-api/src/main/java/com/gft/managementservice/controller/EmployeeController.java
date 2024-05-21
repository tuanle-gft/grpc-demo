package com.gft.managementservice.controller;

import org.springframework.web.bind.annotation.RestController;
import com.gft.openapi.server.employees.api.EmployeesApiController;
import com.gft.managementservice.service.EmployeesApiDelegateImpl;

@RestController
public class EmployeeController extends EmployeesApiController {
    public EmployeeController(EmployeesApiDelegateImpl delegate) {
        super(delegate);
    }
}