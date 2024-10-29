package com.agendahub.agendahub_customer.exception;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException(String message) {
        super(message);
    }
}