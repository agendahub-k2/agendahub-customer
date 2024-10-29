package com.agendahub.agendahub_customer.controller;

import com.agendahub.agendahub_customer.controller.dto.ServiceRequest;
import com.agendahub.agendahub_customer.repository.model.ServiceProviderModel;
import com.agendahub.agendahub_customer.service.ProviderService;
import com.agendahub.agendahub_customer.util.ProviderMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        providerService.createService(ProviderMapper.toDomain(request, id, null));

        logger.info("Service created successfully");

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/service")
    public ResponseEntity<List<ServiceProviderModel>> getServicesByProviderId(@PathVariable Long id) {

        logger.info("Received getServicesByProviderId: {}", id);

        List<ServiceProviderModel> services = providerService.getServicesByProviderId(id);

        logger.info("getServicesByProviderId successfully {}", id);

        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @PutMapping("/service/{serviceId}")
    public ResponseEntity<Void> updateService(@PathVariable Long id, @PathVariable Long serviceId, @Valid @RequestBody ServiceRequest request) {

        logger.info("Received request to update service: {}", request);

        providerService.updateService(ProviderMapper.toDomain(request, id, serviceId));

        logger.info("Service updated successfully");

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/service/{serviceId}")
    public ResponseEntity<Void> removeService(@PathVariable Long id, @PathVariable Long serviceId) {

        logger.info("Received request to remove serviceId: {}", serviceId);

        providerService.removeService(id, serviceId);

        logger.info("Service remove successfully");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<ServiceProviderModel> getServiceByIdAndProviderId(@PathVariable Long id, @PathVariable Long serviceId) {

        logger.info("Received request service by id And providerId: {} {}", serviceId, id);

        ServiceProviderModel serviceByIdAndProviderId = providerService.getServiceByIdAndProviderId(id, serviceId);

        logger.info("Service by id And providerId successfully");

        return new ResponseEntity<>(serviceByIdAndProviderId, HttpStatus.OK);
    }


}
