package com.agendahub.agendahub_customer.repository;

import com.agendahub.agendahub_customer.repository.model.ServiceProviderModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ServiceProviderRepository extends JpaRepository<ServiceProviderModel, String> {

}
