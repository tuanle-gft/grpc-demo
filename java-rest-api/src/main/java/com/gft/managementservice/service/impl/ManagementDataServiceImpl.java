package com.gft.managementservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gft.managementservice.db.entity.Employee;
import com.gft.managementservice.db.repository.EmployeeRepository;
import com.gft.managementservice.dto.EmployeeDto;
import com.gft.managementservice.service.ManagementDataService;
import com.gft.prince.common.utils.CollectionUtil;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ManagementDataServiceImpl implements ManagementDataService {

    private final EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    public ManagementDataServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        log.info("GetEmployees <- Enter");
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        List<Employee> employeeEntities = employeeRepository.findAll();
        if (CollectionUtil.isNullOrEmpty(employeeEntities)) {
            log.info("getEmployees - empty employee -> Leave");
            return employeeDtoList;
        }
        log.info("found {} employees", employeeEntities.size());
        for (Employee employeeEntity : employeeEntities) {
            EmployeeDto employeeDto = this.modelMapper.map(employeeEntity, EmployeeDto.class);
            employeeDtoList.add(employeeDto);
        }
        log.info("GetEmployees -> Leave");
        return employeeDtoList;
    }

    @Override
    public Optional<EmployeeDto> getEmployeeById(Integer id) {
        log.info("getEmployeeById - id: {} <- Enter", id);
        Optional<Employee> employeeEntity = employeeRepository.findById(id);
        if (!employeeEntity.isPresent()) {
            log.info("getEmployeeById - not found employee with id: {} -> Leave", id);
            return Optional.of(null);
        }
        EmployeeDto employeeDto = this.modelMapper.map(employeeEntity, EmployeeDto.class);
        log.info("found employee with id: {}", id);
        log.info("getEmployeeById -> Leave");
        return Optional.of(employeeDto);
    }

}
