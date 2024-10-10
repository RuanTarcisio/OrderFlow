package com.rtarcisio.inventaryms.mappers;

import com.rtarcisio.inventaryms.domains.Inventory;
import com.rtarcisio.inventaryms.domains.Product;
import com.rtarcisio.inventaryms.dtos.ProductDTO;
import com.rtarcisio.inventaryms.dtos.input.ProductInputDetailed;
import com.rtarcisio.inventaryms.enums.CategoryEnum;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

	public static Product inputToProduct(ProductInputDetailed input) {
		Inventory inventory = new Inventory();
		inventory.builder()
				.reorderLevel(input.getReorderLevel())
				.minimumThreshold(input.getMinimumThreshold())
				.availableQuantity(input.getAvailableQuantity())
				.build();

		return Product.builder()
				.name(input.getName())
				.category(CategoryEnum.valueOf(input.getCategory().toUpperCase()))
				.price(input.getPrice())
				.description(input.getDescription())
				.inventory(inventory)
				.build();
				
	}

	public static ProductDTO productToDTO(Product product){
		return null;
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
