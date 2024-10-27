package com.agendahub.agendahub_customer.service;

import com.agendahub.agendahub_customer.repository.ServiceProviderRepository;
import com.agendahub.agendahub_customer.repository.model.ServiceProviderModel;
import com.agendahub.agendahub_customer.util.ProviderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.agendahub.agendahub_customer.domain.ServiceProvedor;

import java.util.List;

@Service
public class ProviderService {

    private static final Logger logger = LoggerFactory.getLogger(ProviderService.class);
    private final ServiceProviderRepository serviceProviderRepository;

    public ProviderService(ServiceProviderRepository repository) {
        serviceProviderRepository = repository;
    }


    public void createService(ServiceProvedor serviceProvedor) {
        serviceProviderRepository.save(ProviderMapper.toModel(serviceProvedor));
    }

    public List<ServiceProviderModel> getServicesByProviderId(Long providerId) {
       return serviceProviderRepository.findByProviderId(providerId);
    }
}
