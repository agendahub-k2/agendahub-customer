package com.agendahub.agendahub_customer.service;

import com.agendahub.agendahub_customer.domain.ServiceProvedor;
import com.agendahub.agendahub_customer.exception.ProviderNotFoundException;
import com.agendahub.agendahub_customer.exception.ServiceNotFoundException;
import com.agendahub.agendahub_customer.repository.ProviderRepository;
import com.agendahub.agendahub_customer.repository.ServiceProviderRepository;
import com.agendahub.agendahub_customer.repository.model.ProviderModel;
import com.agendahub.agendahub_customer.repository.model.ServiceProviderModel;
import com.agendahub.agendahub_customer.util.Constants;
import com.agendahub.agendahub_customer.util.ProviderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProviderService {

    private final ServiceProviderRepository serviceProviderRepository;
    private final ProviderRepository providerRepository;

    public ProviderService(ServiceProviderRepository repository, ProviderRepository providerRepository) {
        this.serviceProviderRepository = repository;
        this.providerRepository = providerRepository;
    }

    @Transactional
    public void createService(ServiceProvedor serviceProvedor) {

        getProvider(serviceProvedor.getProviderId());
        serviceProviderRepository.save(ProviderMapper.toModel(serviceProvedor));
    }

    public List<ServiceProviderModel> getServicesByProviderId(Long providerId) {
        return serviceProviderRepository.findByProviderId(providerId);
    }

    public ProviderModel getProvider(Long id) {
        return providerRepository.findById(id).orElseThrow(() ->
                new ProviderNotFoundException(Constants.PROVIDER_NOT_FOUND));
    }

    @Transactional
    public void updateService(ServiceProvedor serviceProvedor) {
        getProvider(serviceProvedor.getProviderId());

        ServiceProviderModel serviceProviderModel = serviceProviderRepository.findById(serviceProvedor.getId())
                .orElseThrow(() -> new ServiceNotFoundException(Constants.SERVICE_NOT_FOUND));

        serviceProviderModel.setNameService(serviceProvedor.getNameService());
        serviceProviderModel.setDuration(serviceProvedor.getDuration());
        serviceProviderModel.setPrice(serviceProvedor.getPrice());

        serviceProviderRepository.save(serviceProviderModel);
    }

    @Transactional
    public void removeService(Long providerId, Long serviceId) {
        serviceProviderRepository.deleteByIdAndProviderId(serviceId, providerId);
    }

    @Transactional
    public ServiceProviderModel getServiceByIdAndProviderId(Long providerId, Long serviceId) {
        return serviceProviderRepository.getByIdAndProviderId(serviceId, providerId)
                .orElseThrow(() -> new ServiceNotFoundException(Constants.SERVICE_NOT_FOUND));
    }
}
