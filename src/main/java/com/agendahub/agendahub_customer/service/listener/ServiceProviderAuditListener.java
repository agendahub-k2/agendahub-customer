package com.agendahub.agendahub_customer.service.listener;

import com.agendahub.agendahub_customer.repository.ServiceHistoryRepository;
import com.agendahub.agendahub_customer.repository.model.ServiceProviderHistoryModel;
import com.agendahub.agendahub_customer.repository.model.ServiceProviderModel;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
public class ServiceProviderAuditListener {

    private final ServiceHistoryRepository serviceHistoryRepository;

    public ServiceProviderAuditListener(@Lazy ServiceHistoryRepository serviceHistoryRepository) {
        this.serviceHistoryRepository = serviceHistoryRepository;
    }

    @PreUpdate
    public void onUpdate(ServiceProviderModel serviceProviderModel) {
        saveAudit("UPDATE", serviceProviderModel);
    }

    @PreRemove
    public void onDelete(ServiceProviderModel serviceProviderModel) {
        saveAudit("DELETE", serviceProviderModel);
    }

    private void saveAudit(String operation, ServiceProviderModel serviceProviderModel) {
        ServiceProviderHistoryModel serviceProviderHistoryModel = new ServiceProviderHistoryModel();
        serviceProviderHistoryModel.setServiceId(serviceProviderModel.getId());
        serviceProviderHistoryModel.setProviderId(serviceProviderModel.getProviderId());
        serviceProviderHistoryModel.setOperation(operation);
        serviceProviderHistoryModel.setDuration(serviceProviderModel.getDuration());
        serviceProviderHistoryModel.setPrice(serviceProviderModel.getPrice());
        serviceProviderHistoryModel.setNameService(serviceProviderModel.getNameService());

        serviceHistoryRepository.save(serviceProviderHistoryModel);
    }
}
