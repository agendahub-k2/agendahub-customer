package com.agendahub.agendahub_customer.repository;

import com.agendahub.agendahub_customer.repository.model.ServiceProviderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ServiceProviderRepository extends JpaRepository<ServiceProviderModel, Long> {

    List<ServiceProviderModel> findByProviderId(Long id);
    void deleteByIdAndProviderId(Long id, Long providerId);
    Optional<ServiceProviderModel> getByIdAndProviderId(Long id, Long providerId);
}
