package com.agendahub.agendahub_customer.util;

import com.agendahub.agendahub_customer.controller.dto.UserRequest;
import com.agendahub.agendahub_customer.controller.dto.UserResponse;
import com.agendahub.agendahub_customer.domain.Provider;
import com.agendahub.agendahub_customer.domain.User;
import com.agendahub.agendahub_customer.repository.model.ProviderModel;
import com.agendahub.agendahub_customer.repository.model.UserModel;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


public class UserMapper {

    public static User toUser(UserRequest userRequest) {
        User user = new User();
        user.setId(null);
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setUserType(User.UserType.valueOf(userRequest.getUserType().toUpperCase()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdateAt(LocalDateTime.now());

        if (Objects.equals(userRequest.getUserType(), User.UserType.PROVEDOR.name())) {
            Provider provider = new Provider();
            provider.setId(null);
            provider.setTypeProvider(userRequest.getProviderRequest().getTypeProvider());
            provider.setNumero(userRequest.getProviderRequest().getNumero());
            provider.setDescricaoRua(userRequest.getProviderRequest().getDescricaoRua());
            provider.setNumCep(userRequest.getProviderRequest().getNumCep());
            user.setProvider(provider);
        }

        return user;
    }

    public static UserResponse toUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setUserType(user.getUserType().name());
        userResponse.setCreatedAt(user.getCreatedAt().toString());
        userResponse.setUpdateAt(user.getCreatedAt().toString());
        return userResponse;
    }

    public static UserModel toModel(User user) {
        UserModel model = new UserModel();
        model.setId(user.getId());
        model.setName(user.getName());
        model.setEmail(user.getEmail());
        model.setUserType(user.getUserType());
        model.setCreatedAt(user.getCreatedAt());
        model.setUpdateAt(user.getUpdateAt());
        model.setPassword(user.getPassword());
        model.setProvider(getProviderModel(user));

        return model;
    }

    private static ProviderModel getProviderModel(User user) {
        ProviderModel provider = new ProviderModel();
        if (user.getUserType() == User.UserType.PROVEDOR) {
            provider.setTypeProvider(user.getProvider().getTypeProvider());
            provider.setNumero(user.getProvider().getNumero());
            provider.setDescricaoRua(user.getProvider().getDescricaoRua());
            provider.setNumCep(user.getProvider().getNumCep());
        } else {
            provider.setId(user.getProvider().getId());
        }
        return provider;
    }

    public static User toUser(UserModel model) {
        User user = new User();
        user.setId(model.getId());
        user.setName(model.getName());
        user.setEmail(model.getEmail());
        user.setUserType(model.getUserType());
        user.setCreatedAt(model.getCreatedAt());
        user.setUpdateAt(model.getUpdateAt());
        user.setPassword(model.getPassword());
        return user;
    }
}

