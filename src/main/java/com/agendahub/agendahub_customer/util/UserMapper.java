package com.agendahub.agendahub_customer.util;

import com.agendahub.agendahub_customer.controller.dto.UserRequest;
import com.agendahub.agendahub_customer.controller.dto.UserResponse;
import com.agendahub.agendahub_customer.domain.User;
import com.agendahub.agendahub_customer.repository.model.UserModel;

import java.time.LocalDateTime;
import java.util.UUID;


public class UserMapper {

    public static User toUser(UserRequest userRequest) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setUserType(User.UserType.valueOf(userRequest.getUserType().toUpperCase()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdateAt(LocalDateTime.now());
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

    public static UserModel toModel(User user){
        UserModel model = new UserModel();
        model.setId(user.getId());
        model.setName(user.getName());
        model.setEmail(user.getEmail());
        model.setUserType(user.getUserType());
        model.setCreatedAt(user.getCreatedAt());
        model.setUpdateAt(user.getUpdateAt());
        model.setProviderId(1L);
        model.setPassword(user.getPassword());
        return model;
    }

    public static User toUser(UserModel model){
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

