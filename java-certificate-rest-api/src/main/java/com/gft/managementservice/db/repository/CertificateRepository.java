package com.gft.managementservice.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.managementservice.db.entity.CertificateEntity;

@Repository
public interface CertificateRepository extends JpaRepository<CertificateEntity, Integer> {
    List<CertificateEntity> findByEmployeeId(Integer employeeId);
}
