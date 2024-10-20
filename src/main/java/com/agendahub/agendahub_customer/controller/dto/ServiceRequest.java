package com.agendahub.agendahub_customer.controller.dto;

import java.lang.annotation.ElementType;
import java.math.BigDecimal;

import jakarta.validation.Payload;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Constraint;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Data
@ToString
@NoArgsConstructor
public class ServiceRequest {

    @NotBlank(message = "nameService cannot be empty or null")
    @Size(max = 100, message = "nameService should not be longer than 100 characters")
    private String nameService;

    @MaxDuration(message = "duration cannot exceed 3 hours")
    @TwoDecimalPlaces(message = "Duration must have at most two decimal places")
    private BigDecimal duration;

    @NotNull(message = "price cannot be null")
    @DecimalMin(value = "0.01", message = "price must be greater than 0")
    @TwoDecimalPlaces(message = "Price must have at most two decimal places")
    private BigDecimal price;

}

@Constraint(validatedBy = TwoDecimalPlacesValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@interface TwoDecimalPlaces {
    String message() default "Must have at most two decimal places";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

class TwoDecimalPlacesValidator implements ConstraintValidator<TwoDecimalPlaces, BigDecimal> {

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return value.scale() <= 2;
    }
}


@Constraint(validatedBy = DurationValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@interface MaxDuration {
    String message() default "Duration cannot exceed 3 hours";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


class DurationValidator implements ConstraintValidator<MaxDuration, BigDecimal> {

    @Override
    public boolean isValid(BigDecimal duration, ConstraintValidatorContext context) {

        if (duration == null) {
            return true;
        }
        return duration.compareTo(BigDecimal.valueOf(180)) <= 0;
    }
}
