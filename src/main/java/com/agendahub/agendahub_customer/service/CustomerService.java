package com.agendahub.agendahub_customer.service;

import com.agendahub.agendahub_customer.controller.dto.LoginResponse;
import com.agendahub.agendahub_customer.domain.User;
import com.agendahub.agendahub_customer.domain.UserAuthenticated;
import com.agendahub.agendahub_customer.exception.ProviderNotFoundException;
import com.agendahub.agendahub_customer.exception.UnauthorizedException;
import com.agendahub.agendahub_customer.exception.UserNotFoundException;
import com.agendahub.agendahub_customer.repository.ProviderRepository;
import com.agendahub.agendahub_customer.repository.UserRepository;
import com.agendahub.agendahub_customer.repository.model.ProviderModel;
import com.agendahub.agendahub_customer.repository.model.UserModel;
import com.agendahub.agendahub_customer.util.Constants;
import com.agendahub.agendahub_customer.util.JwtUtil;
import com.agendahub.agendahub_customer.util.UserMapper;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomerService {

    private final UserRepository repository;
    private final ProviderRepository providerRepository;
    private final PasswordService passwordService;
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public CustomerService(UserRepository repository, PasswordService passwordService, ProviderRepository providerRepository) {
        this.repository = repository;
        this.passwordService = passwordService;
        this.providerRepository = providerRepository;
    }

    public User createUser(User user) {

        try {
            setPasswordEncrypt(user);

            UserModel model = UserMapper.toModel(user);
            setProvider(model, user.getProvider().getId());

            repository.save(model);
            logger.info("User persisted with ID: {}", user.getId());
        } catch (Exception e) {
            logger.error("Failed to save user with ID: {}. Exception: {}", user.getId(), e.getMessage(), e);
            throw e;
        }

        return user;
    }

    public UserAuthenticated login(String email, String password) {
        UserAuthenticated userAuthenticated = new UserAuthenticated();
        try {

            Optional<UserModel> userModel = repository.findByEmail(email);

            if (userModel.isPresent()) {
                boolean isValid = passwordService.checkPassword(password, userModel.get().getPassword());
                if (isValid) {
                    userAuthenticated = UserMapper.toUserAuthenticated(JwtUtil.generateToken(email), userModel.get());
                } else {
                    new LoginResponse("Credenciais inválidas");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userAuthenticated;
    }

    public User getUserDataByEmail(String email, String token) {
        try {
            DecodedJWT decodedJWT = JwtUtil.verifyToken(token);
            String emailToken = decodedJWT.getSubject();

            if (!email.equals(emailToken)) {
                throw new UnauthorizedException(email, token);
            }

            UserModel user = repository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND + email));
            return UserMapper.toUser(user);
        } catch (JWTDecodeException ex) {
            throw new UnauthorizedException(email, token);
        }
    }

    public User getUserByToken(String token) {
        try {
            DecodedJWT decodedJWT = JwtUtil.verifyToken(token);
            String emailToken = decodedJWT.getSubject();

            UserModel user = repository.findByEmail(emailToken).orElseThrow(() -> new UserNotFoundException(Constants.USER_NOT_FOUND + emailToken));
            return UserMapper.toUser(user);
        } catch (JWTDecodeException ex) {
            throw new UnauthorizedException(token);
        }
    }

    private void setPasswordEncrypt(User user) {
        user.setPassword(passwordService.hashPassword(user.getPassword()));
    }

    private void setProvider(UserModel model, Long id) {
        if (model.getUserType() == User.UserType.SOLICITANTE) {
            ProviderModel providerModel = providerRepository.findById(id).orElseThrow(() ->
                    new ProviderNotFoundException(Constants.PROVIDER_NOT_FOUND));
            model.setProvider(providerModel);
        }
    }

}
