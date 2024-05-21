package com.gft.managementservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gft.managementservice.db.entity.CertificateEntity;
import com.gft.managementservice.db.repository.CertificateRepository;
import com.gft.managementservice.utils.CommonUtils;
import com.gft.openapi.server.certificates.api.CertificatesApiDelegate;
import com.gft.openapi.server.certificates.model.Certificate;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CertificatesApiDelegateImpl implements CertificatesApiDelegate {

    private final CertificateRepository certificateRepository;
    private ModelMapper modelMapper;

    public CertificatesApiDelegateImpl(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public ResponseEntity<List<Certificate>> certificatesEmployeeIdGet(Integer employeeId) {
        log.info("certificatesEmployeeIdGet - employeeId: {} <- Enter", employeeId);
        List<Certificate> certificates = new ArrayList<>();
        List<CertificateEntity> certificateEntities = certificateRepository.findByEmployeeId(employeeId);
        if (CommonUtils.isNullOrEmpty(certificateEntities)) {
            log.info("certificatesEmployeeIdGet - empty certificates -> Leave");
            return ResponseEntity.ok(certificates);
        }
        log.info("found {} certificates", certificateEntities.size());
        for (CertificateEntity certificateEntity : certificateEntities) {
            Certificate certificateDto = this.modelMapper.map(certificateEntity, Certificate.class);
            certificates.add(certificateDto);
        }
        log.info("certificatesEmployeeIdGet -> Leave");
        return ResponseEntity.ok(certificates);
    }
}
