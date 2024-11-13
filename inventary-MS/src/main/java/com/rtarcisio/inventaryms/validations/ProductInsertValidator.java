package com.rtarcisio.inventaryms.validations;

import com.rtarcisio.inventaryms.dtos.input.ProductInputSimple;
import com.rtarcisio.inventaryms.enums.CategoryEnum;
import com.rtarcisio.inventaryms.services.exceptions.templ.FieldMessage;
import com.rtarcisio.inventaryms.utils.ProductValidationUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProductInsertValidator implements ConstraintValidator<ProductInsert, ProductInputSimple> {

	@Override
	public boolean isValid(ProductInputSimple input, ConstraintValidatorContext context) {
		Set<FieldMessage> list = ProductValidationUtils.validateProductInputSimple(input);

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
					.addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

