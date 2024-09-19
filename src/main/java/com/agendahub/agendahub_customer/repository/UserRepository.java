package com.agendahub.agendahub_customer.repository;

import com.agendahub.agendahub_customer.repository.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserModel, String> {

    Optional<UserModel> findByEmail(String email);
}
