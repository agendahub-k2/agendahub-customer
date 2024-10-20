package com.agendahub.agendahub_customer.controller.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ConditionalValidProviderRequestValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ConditionalValidProviderRequest {
    String message() default "Invalid ProviderRequest";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
