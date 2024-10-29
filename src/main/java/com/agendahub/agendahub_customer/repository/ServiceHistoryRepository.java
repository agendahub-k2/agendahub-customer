package com.agendahub.agendahub_customer.repository;

import com.agendahub.agendahub_customer.repository.model.ServiceProviderHistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceHistoryRepository extends JpaRepository<ServiceProviderHistoryModel, Long> {
}
