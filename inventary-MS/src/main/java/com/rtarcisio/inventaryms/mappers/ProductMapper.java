package com.rtarcisio.inventaryms.mappers;

import com.rtarcisio.inventaryms.domains.Inventory;
import com.rtarcisio.inventaryms.domains.Product;
import com.rtarcisio.inventaryms.dtos.input.ProductInput;
import com.rtarcisio.inventaryms.enums.CategoryEnum;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

	public static Product inputToProduct(ProductInput input) {
		Inventory inventory = new Inventory();
		inventory.builder()
				.reorderLevel(input.reorderLevel())
				.minimumThreshold(input.minimumThreshold())
				.availableQuantity(input.availableQuantity())
				.build();

		return Product.builder()
				.name(input.name())
				.category(CategoryEnum.valueOf(input.category().toUpperCase()))
				.price(input.price())
				.description(input.description())
				.inventory(inventory)
				.build();
				
	}
//
//	 public static PersonDto personToDTO(Person person){
//	        return PersonDto.builder()
//					.id(person.getId())
//					.cpf(person.getCpf())
//					.email(person.getEmail())
//					.nomeCompleto(person.getName())
//					.dataNascimento(person.getDataNascimento())
//	                .build();
//	    }
}
