package com.agendahub.agendahub_customer.repository;

import com.agendahub.agendahub_customer.repository.model.ServiceProviderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ServiceProviderRepository extends JpaRepository<ServiceProviderModel, Long> {

    List<ServiceProviderModel> findByProviderId(Long id);
}
