package com.rtarcisio.inventaryms.validations;

import com.rtarcisio.inventaryms.dtos.input.ProductInput;
import com.rtarcisio.inventaryms.enums.CategoryEnum;
import com.rtarcisio.inventaryms.services.exceptions.templ.FieldMessage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductInsertValidator implements ConstraintValidator<ProductInsert, ProductInput> {

	private List<String> allowedContentTypes;

	@Override
	public boolean isValid(ProductInput input, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<FieldMessage>();

		try {
			CategoryEnum.valueOf(input.category().toUpperCase());  // Faz a validação ignorando case

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
