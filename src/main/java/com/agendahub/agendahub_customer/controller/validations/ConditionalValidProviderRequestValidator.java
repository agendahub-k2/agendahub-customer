package com.agendahub.agendahub_customer.controller.validations;

import com.agendahub.agendahub_customer.controller.dto.ProviderRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConditionalValidProviderRequestValidator implements ConstraintValidator<ConditionalValidProviderRequest, ProviderRequest> {

    @Override
    public void initialize(ConditionalValidProviderRequest constraintAnnotation) {}

    @Override
    public boolean isValid(ProviderRequest providerRequest, ConstraintValidatorContext context) {
        if (providerRequest == null) {
            return true; // Se for null, é válido
        }
        
        return providerRequest.getNumCep() != null && !providerRequest.getNumCep().isEmpty() &&
                providerRequest.getDescricaoRua() != null && !providerRequest.getDescricaoRua().isEmpty() &&
                providerRequest.getNumero() != null && !providerRequest.getNumero().isEmpty() &&
                providerRequest.getTypeProvider() != null && !providerRequest.getTypeProvider().isEmpty();
    }
}
