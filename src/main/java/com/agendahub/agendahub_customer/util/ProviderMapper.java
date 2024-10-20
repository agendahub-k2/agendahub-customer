package com.agendahub.agendahub_customer.util;

import com.agendahub.agendahub_customer.controller.dto.ServiceRequest;
import com.agendahub.agendahub_customer.domain.ServiceProvedor;
import com.agendahub.agendahub_customer.domain.User;
import com.agendahub.agendahub_customer.repository.model.ServiceProviderModel;
import com.agendahub.agendahub_customer.repository.model.UserModel;

public class ProviderMapper {

    public static ServiceProvedor toDomain(ServiceRequest request, Long id) {
        ServiceProvedor provider = new ServiceProvedor();
        provider.setProviderId(id);
        provider.setDuration(request.getDuration());
        provider.setNameService(request.getNameService());
        provider.setPrice(request.getPrice());

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
}