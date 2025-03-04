package com.rtarcisio.inventaryms.validations;

import com.rtarcisio.inventaryms.dtos.input.SkuInput;
import com.rtarcisio.inventaryms.services.exceptions.templ.FieldMessage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileContentDetailedValidator implements ConstraintValidator<FileContentDetailed, SkuInput> {

	private List<String> allowedContentTypes;
	private DataSize maxSize;

	@Override
	public void initialize(FileContentDetailed constraint) {
		this.allowedContentTypes = Arrays.asList(constraint.allowed());
		this.maxSize = DataSize.parse(constraint.max());
	}

	@Override
	public boolean isValid(SkuInput input, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

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