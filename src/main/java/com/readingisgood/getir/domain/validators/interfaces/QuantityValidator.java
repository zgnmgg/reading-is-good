package com.readingisgood.getir.domain.validators.interfaces;

import com.readingisgood.getir.domain.validators.ValidQuantity;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = ValidQuantity.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface QuantityValidator {

    String message() default "INVALID.quantity";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
