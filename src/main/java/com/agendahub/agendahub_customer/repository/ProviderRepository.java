package com.agendahub.agendahub_customer.repository;

import com.agendahub.agendahub_customer.repository.model.ProviderModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProviderRepository extends JpaRepository<ProviderModel, Long> {
}
