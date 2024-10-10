package com.rtarcisio.inventaryms.validations;

import com.rtarcisio.inventaryms.dtos.input.ProductInputDetailed;
import com.rtarcisio.inventaryms.dtos.input.ProductInputSimple;
import com.rtarcisio.inventaryms.enums.CategoryEnum;
import com.rtarcisio.inventaryms.services.exceptions.templ.FieldMessage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class ProductInsertValidator implements ConstraintValidator<ProductInsert, ProductInputSimple> {

	@Override
	public void initialize(ProductInsert constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(ProductInputSimple input, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<FieldMessage>();

		try {
			CategoryEnum.valueOf(input.getCategory().toUpperCase());  // Faz a validação ignorando case

		} catch (IllegalArgumentException e) {
			list.add(new FieldMessage("category", "Invalid Category"));
		}



		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

