package com.rtarcisio.inventaryms.mappers;

import com.rtarcisio.inventaryms.domains.ImageProduct;
import com.rtarcisio.inventaryms.domains.ProductGroup;
import com.rtarcisio.inventaryms.dtos.ImageDTO;
import com.rtarcisio.inventaryms.dtos.ProductSimpleDTO;
import com.rtarcisio.inventaryms.dtos.ProductDetailedDTO;
import com.rtarcisio.inventaryms.dtos.input.ProductInputSimple;
import com.rtarcisio.inventaryms.enums.CategoryEnum;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class ProductMapper {

//	public static ProductGroup inputDetailedToProduct(ProductSkuInputDetailed input) throws IOException {
//		Inventory inventory = Inventory.builder()
//				.reorderLevel(input.getReorderLevel())
//				.minimumThreshold(input.getMinimumThreshold())
//				.availableQuantity(input.getAvailableQuantity())
//				.inStock(input.getAvailableQuantity())
//				.build();
//
//		return buildProduct(input.getName(), input.getCategory(), input.getPrice(), input.getDescription(), inventory);
//	}

	public static ProductGroup inputSimpleToProduct(ProductInputSimple input) {
		try {
            return ProductGroup.builder()
					.name(input.getName())
					.category(CategoryEnum.valueOf(input.getCategory().toUpperCase()))
					.description(input.getDescription())
					.build();
		}catch (Exception e){
			throw new IllegalArgumentException("Invalid Category");
		}
	}

	public static ProductSimpleDTO productToDTO(ProductGroup productGroup){

		return null;
	}


	public static ProductDetailedDTO toDetailedDTO(ProductGroup productGroup, List<ImageProduct> imageProductsList) throws IOException {
		List<ImageDTO> imageDTOList = imageProductsList.isEmpty() ?
				List.of(ImageMapper.createDefaultImage()) :
				ImageMapper.mapToImageDTOList(ImageMapper.mapToMultipartFileList(imageProductsList));

		return buildProductDetailedDTO(productGroup, imageDTOList);
	}

	private static ProductDetailedDTO buildProductDetailedDTO(ProductGroup productGroup, List<ImageDTO> imageDTOList) {
		return ProductDetailedDTO.builder()
				.id(productGroup.getId())
				.name(productGroup.getName())
				.category(productGroup.getCategory().name())
				.files(imageDTOList)
//				.description(productGroup.getDescription())
//				.price(productGroup.getPrice())
//				.availableQuantity(productGroup.getInventory().getAvailableQuantity())
				.build();
	}


}
