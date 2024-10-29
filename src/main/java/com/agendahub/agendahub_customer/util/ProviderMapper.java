package com.agendahub.agendahub_customer.util;

import com.agendahub.agendahub_customer.controller.dto.ProviderResponse;
import com.agendahub.agendahub_customer.controller.dto.ServiceRequest;
import com.agendahub.agendahub_customer.domain.Provider;
import com.agendahub.agendahub_customer.domain.ServiceProvedor;
import com.agendahub.agendahub_customer.repository.model.ServiceProviderModel;

public class ProviderMapper {

    public static ServiceProvedor toDomain(ServiceRequest request, Long providerId, Long serviceId) {
        ServiceProvedor provider = new ServiceProvedor();
        provider.setProviderId(providerId);
        provider.setDuration(request.getDuration());
        provider.setNameService(request.getNameService());
        provider.setPrice(request.getPrice());
        provider.setId(serviceId);

        return provider;
    }

    public static ServiceProviderModel toModel(ServiceProvedor serviceProvedor){
        ServiceProviderModel model = new ServiceProviderModel();
        model.setProviderId(serviceProvedor.getProviderId());
        model.setDuration(serviceProvedor.getDuration());
        model.setPrice(serviceProvedor.getPrice());
        model.setNameService(serviceProvedor.getNameService());

        return model;
    }

    public static ProviderResponse toResponse(Provider provider){
        ProviderResponse providerResponse = new ProviderResponse();
        providerResponse.setTypeProvider(provider.getTypeProvider());
        providerResponse.setNumero(provider.getNumero());
        providerResponse.setDescricaoRua(provider.getDescricaoRua());
        providerResponse.setNumCep(provider.getNumCep());
        providerResponse.setId(provider.getId());

        return providerResponse;
    }
}
