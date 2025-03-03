package com.rtarcisio.inventaryms.validations;

import com.rtarcisio.inventaryms.dtos.input.ProductSkuInputDetailed;
import com.rtarcisio.inventaryms.services.exceptions.templ.FieldMessage;
import com.rtarcisio.inventaryms.utils.ProductValidationUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ProductDetailedInsertValidator implements ConstraintValidator<ProductDetailedInsert, ProductSkuInputDetailed> {

	private List<String> allowedContentTypes;
	private DataSize maxSize;

	@Override
	public void initialize(ProductDetailedInsert constraint) {
		this.allowedContentTypes = Arrays.asList(constraint.allowed());
		this.maxSize = DataSize.parse(constraint.max());
	}

	@Override
	public boolean isValid(ProductSkuInputDetailed input, ConstraintValidatorContext context) {
		Set<FieldMessage> list = ProductValidationUtils.validateProductInputSimple(input);

		List<MultipartFile> multipartFiles = input.getFiles();
		if(multipartFiles != null) {
			multipartFiles.forEach(multipartFile -> {
				if (multipartFile != null && (multipartFile.getContentType() != null
						&& !this.allowedContentTypes.contains(multipartFile.getContentType()))) {
					list.add(new FieldMessage("files", "Invalid File Category"));
				}

				if (multipartFile != null && multipartFile.getSize() > this.maxSize.toBytes()) {
					list.add(new FieldMessage("files", "Invalid Size"));
				}
			});
		}


		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
					.addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}