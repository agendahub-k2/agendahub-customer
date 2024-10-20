package com.agendahub.agendahub_customer.controller;

import com.agendahub.agendahub_customer.controller.dto.ServiceRequest;
import com.agendahub.agendahub_customer.service.ProviderService;
import com.agendahub.agendahub_customer.util.ProviderMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/provider/{id}")
public class ProviderController {

    private final ProviderService providerService;
    private static final Logger logger = LoggerFactory.getLogger(ProviderController.class);

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping("/service")
    public ResponseEntity<Void> createService(@PathVariable Long id, @Valid @RequestBody ServiceRequest request) {

        logger.info("Received request to create service: {}", request);

        providerService.createService(ProviderMapper.toDomain(request, id));

        logger.info("Service created successfully");

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PostMapping("/service")
    public ResponseEntity<String> getServicesByProviderId(@PathVariable Long providerId) {

        logger.info("Received getServicesByProviderId: {}", providerId);

        providerService.getServicesByProviderId(providerId);

        logger.info("getServicesByProviderId successfully {}", providerId);

        return new ResponseEntity<>("", HttpStatus.OK);
    }


}
