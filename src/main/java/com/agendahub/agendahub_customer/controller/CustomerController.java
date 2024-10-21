package com.agendahub.agendahub_customer.controller;

import com.agendahub.agendahub_customer.controller.dto.LoginRequest;
import com.agendahub.agendahub_customer.controller.dto.LoginResponse;
import com.agendahub.agendahub_customer.controller.dto.UserRequest;
import com.agendahub.agendahub_customer.controller.dto.UserResponse;
import com.agendahub.agendahub_customer.domain.User;
import com.agendahub.agendahub_customer.domain.UserAuthenticated;
import com.agendahub.agendahub_customer.exception.UnauthorizedException;
import com.agendahub.agendahub_customer.service.CustomerService;
import com.agendahub.agendahub_customer.util.Constants;
import com.agendahub.agendahub_customer.util.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class CustomerController {

    private final CustomerService customerService;
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {

        logger.info("Received request to create user: {}", userRequest);

        User user = customerService.createUser(UserMapper.toUser(userRequest));

        logger.info("User created successfully with ID: {}", user);

        return new ResponseEntity<>(UserMapper.toUserResponse(user), HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email, HttpServletRequest request) {

        String token = extractTokenFromRequest(request);
        if (token == null) {
            throw new UnauthorizedException();
        }

        return ResponseEntity.ok(UserMapper.toUserResponse(customerService.getUserDataByEmail(email, token)));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {

        logger.info("Received request to login user: {}", loginRequest.getEmail());

        UserAuthenticated userAuthenticated = customerService.login(loginRequest.getEmail(), loginRequest.getPassword());

        return ResponseEntity.ok(UserMapper.toLoginResponse(userAuthenticated));
    }

    @GetMapping("/authenticate")
    public ResponseEntity<UserResponse> authenticate(HttpServletRequest request) {

        String tokenFromRequest = extractTokenFromRequest(request);
        if (tokenFromRequest == null) {
            throw new UnauthorizedException();
        }
        return ResponseEntity.ok(UserMapper.toUserResponse(customerService.getUserByToken(tokenFromRequest)));
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7); // Remove "Bearer "
        }
        return null;
    }

}
