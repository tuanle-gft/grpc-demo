package com.gft.managementservice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.gft.managementservice.service.CertificatesApiDelegateImpl;
import com.gft.openapi.server.certificates.api.CertificatesApiController;

@RestController
public class CertificateController extends CertificatesApiController {
    public CertificateController(CertificatesApiDelegateImpl delegate) {
        super(delegate);
    }
}