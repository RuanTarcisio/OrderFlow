package com.rtarcisio.orderms.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = { OrderInputInsertValidator.class })
public @interface OrderInputInsert {

	String message() default "arquivo inválido";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}