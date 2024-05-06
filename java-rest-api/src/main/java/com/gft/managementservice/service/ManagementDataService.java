package com.gft.managementservice.service;

import java.util.List;
import java.util.Optional;

import com.gft.managementservice.dto.EmployeeDto;

public interface ManagementDataService {
    List<EmployeeDto> getEmployees();
    Optional<EmployeeDto> getEmployeeById(Integer id);
}
