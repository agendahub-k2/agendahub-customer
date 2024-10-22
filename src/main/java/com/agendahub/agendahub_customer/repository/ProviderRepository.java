package com.agendahub.agendahub_customer.repository;

import com.agendahub.agendahub_customer.repository.model.ProviderModel;
import com.agendahub.agendahub_customer.repository.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProviderRepository extends JpaRepository<ProviderModel, Long> {
}
