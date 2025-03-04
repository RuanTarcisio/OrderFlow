package com.rtarcisio.inventaryms.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = { FileContentDetailedValidator.class })
public @interface FileContentDetailed {

	String message() default "produto inválido";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	String[] allowed();

	String max();


}
