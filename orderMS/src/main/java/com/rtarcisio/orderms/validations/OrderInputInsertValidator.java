package com.rtarcisio.orderms.validations;

import com.rtarcisio.orderms.dtos.inputs.OrderInput;
import com.rtarcisio.orderms.exceptions.templates.FieldMessage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderInputInsertValidator implements ConstraintValidator<OrderInputInsert, OrderInput> {

	@Override
	public void initialize(OrderInputInsert constraint) {}
	
	@Override
	public boolean isValid(OrderInput orderInput, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<FieldMessage>();

		if(orderInput.getProducts().isEmpty()){
			list.add(new FieldMessage("products", "No products in this order."));
		}

		if(orderInput.getId_user() == null){
			list.add(new FieldMessage("id_user", "No user id in this order."));
		}

		if(orderInput.getPaymentMethod() == null){
			list.add(new FieldMessage("paymentMethod", "No has payment method or is wrong this order."));
		}


		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
