package com.gft.managementservice.service;

import com.gft.openapi.server.employees.model.Certificate;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gft.managementservice.db.entity.EmployeeEntity;
import com.gft.managementservice.db.repository.EmployeeRepository;
import com.gft.managementservice.utils.CommonUtils;

import com.gft.openapi.server.employees.api.EmployeesApiDelegate;
import com.gft.openapi.server.employees.model.Employee;
import com.gft.openapi.client.certificates.api.DefaultApi;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeesApiDelegateImpl implements EmployeesApiDelegate {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final DefaultApi certificateApi;

    public EmployeesApiDelegateImpl(EmployeeRepository employeeRepository, DefaultApi certificateApi) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = new ModelMapper();
        this.certificateApi = certificateApi;
    }

    @Override
    public ResponseEntity<List<Employee>> employeesGet() {
        log.info("employeesGet <- Enter");
        List<Employee> employeeDtoList = new ArrayList<>();
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        if (CommonUtils.isNullOrEmpty(employeeEntities)) {
            log.info("employeesGet - empty employee -> Leave");
            return ResponseEntity.ok(employeeDtoList);
        }
        log.info("found {} employees", employeeEntities.size());
        for (EmployeeEntity employeeEntity : employeeEntities) {
            Employee employeeDto = this.modelMapper.map(employeeEntity, Employee.class);
            FetchCertificates(employeeDto);
            employeeDtoList.add(employeeDto);
        }
        log.info("employeesGet -> Leave");
        return ResponseEntity.ok(employeeDtoList);
    }

    @Override
    public ResponseEntity<Employee> employeesIdGet(Integer id) {
        log.info("employeesIdGet - id: {} <- Enter", id);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        if (employeeEntity.isEmpty()) {
            log.info("employeesIdGet - not found employee with id: {} -> Leave", id);
            return ResponseEntity.notFound().build();
        }
        Employee employeeDto = this.modelMapper.map(employeeEntity, Employee.class);
        log.info("found employee with id: {}", id);
        FetchCertificates(employeeDto);
        log.info("employeesIdGet -> Leave");
        return ResponseEntity.ok(employeeDto);
    }

    private void FetchCertificates(Employee employee) {
        List<com.gft.openapi.client.certificates.model.Certificate> certificates = certificateApi.certificatesEmployeeIdGet(employee.getId());
        if (!CommonUtils.isNullOrEmpty(certificates)) {
            log.info("found {} certificates for employeeId {}", certificates.size(), employee.getId());
            for (com.gft.openapi.client.certificates.model.Certificate clientCertificate : certificates) {
                Certificate certificate = this.modelMapper.map(clientCertificate, Certificate.class);
                employee.addCertificatesItem(certificate);
            }
        }
    }
}
