package com.agendahub.agendahub_customer.util;

import com.agendahub.agendahub_customer.controller.dto.LoginResponse;
import com.agendahub.agendahub_customer.controller.dto.UserRequest;
import com.agendahub.agendahub_customer.controller.dto.UserResponse;
import com.agendahub.agendahub_customer.domain.Provider;
import com.agendahub.agendahub_customer.domain.User;
import com.agendahub.agendahub_customer.domain.UserAuthenticated;
import com.agendahub.agendahub_customer.repository.model.ProviderModel;
import com.agendahub.agendahub_customer.repository.model.UserModel;

import java.time.LocalDateTime;
import java.util.Objects;


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
        user.setPhone(userRequest.getPhone());

        Provider provider = getProvider(userRequest);
        user.setProvider(provider);

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
        userResponse.setPhone(user.getPhone());
        return userResponse;
    }

    public static LoginResponse toLoginResponse(UserAuthenticated user) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(user.getToken());
        loginResponse.setId(user.getId());
        loginResponse.setName(user.getName());
        loginResponse.setEmail(user.getEmail());
        loginResponse.setUserType(user.getUserType().name());
        loginResponse.setCreatedAt(user.getCreatedAt().toString());
        loginResponse.setUpdateAt(user.getUpdateAt().toString());
        loginResponse.setPhone(user.getPhone());
        loginResponse.setProviderResponse(ProviderMapper.toResponse(user.getProvider()));
        return loginResponse;
    }

    public static UserModel toModel(User user) {
        UserModel model = new UserModel();
        model.setId(user.getId());
        model.setName(user.getName());
        model.setEmail(user.getEmail());
        model.setPhone(user.getPhone());
        model.setUserType(user.getUserType());
        model.setCreatedAt(user.getCreatedAt());
        model.setUpdateAt(user.getUpdateAt());
        model.setPassword(user.getPassword());
        model.setProvider(getProviderModel(user));

        return model;
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
        user.setPhone(model.getPhone());
        return user;
    }

    private static Provider getProvider(UserRequest userRequest) {
        Provider provider = new Provider();
        if (Objects.equals(userRequest.getUserType(), User.UserType.PROVEDOR.name())) {
            provider.setId(null);
            provider.setTypeProvider(userRequest.getProviderRequest().getTypeProvider());
            provider.setNumero(userRequest.getProviderRequest().getNumero());
            provider.setDescricaoRua(userRequest.getProviderRequest().getDescricaoRua());
            provider.setNumCep(userRequest.getProviderRequest().getNumCep());
        } else {
            provider.setId(userRequest.getProviderRequest().getId());
        }
        return provider;
    }

    private static ProviderModel getProviderModel(User user) {
        ProviderModel provider = new ProviderModel();
        if (user.getUserType() == User.UserType.PROVEDOR) {
            provider.setTypeProvider(user.getProvider().getTypeProvider());
            provider.setNumero(user.getProvider().getNumero());
            provider.setDescricaoRua(user.getProvider().getDescricaoRua());
            provider.setNumCep(user.getProvider().getNumCep());
        }
        return provider;
    }

    public static UserAuthenticated toUserAuthenticated(String token, UserModel userModel) {
        UserAuthenticated userAuthenticated = new UserAuthenticated();
        userAuthenticated.setToken(token);
        userAuthenticated.setId(userModel.getId());
        userAuthenticated.setUserType(userModel.getUserType());
        userAuthenticated.setName(userModel.getName());
        userAuthenticated.setEmail(userModel.getEmail());
        userAuthenticated.setPhone(userModel.getPhone());
        userAuthenticated.setCreatedAt(userModel.getCreatedAt());
        userAuthenticated.setUpdateAt(userModel.getUpdateAt());

        Provider provider = new Provider();
        provider.setId(userModel.getProvider().getId());
        provider.setNumero(userModel.getProvider().getNumero());
        provider.setNumCep(userModel.getProvider().getNumCep());
        provider.setTypeProvider(userModel.getProvider().getTypeProvider());
        provider.setDescricaoRua(userModel.getProvider().getDescricaoRua());


        userAuthenticated.setProvider(provider);

        return userAuthenticated;
    }
}

