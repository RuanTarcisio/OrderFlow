package com.rtarcisio.inventaryms.mappers;

import com.rtarcisio.inventaryms.domains.ImageProduct;
import com.rtarcisio.inventaryms.domains.Inventory;
import com.rtarcisio.inventaryms.domains.ProductGroup;
import com.rtarcisio.inventaryms.dtos.ImageDTO;
import com.rtarcisio.inventaryms.dtos.ProductDTO;
import com.rtarcisio.inventaryms.dtos.ProductDetailedDTO;
import com.rtarcisio.inventaryms.dtos.input.ProductSkuInputDetailed;
import com.rtarcisio.inventaryms.dtos.input.ProductInputSimple;
import com.rtarcisio.inventaryms.enums.CategoryEnum;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
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
		return buildProduct(input.getName(), input.getCategory(), input.getDescription());
	}

	private static ProductGroup buildProduct(String name, String category, String description) {
		return ProductGroup.builder()
				.name(name)
				.category(CategoryEnum.valueOf(category.toUpperCase()))
				.description(description)
				.build();
	}

	public static ProductDTO productToDTO(ProductGroup productGroup){

		return null;
	}


	public static ProductDetailedDTO toDetailedDTO(ProductGroup productGroup, List<ImageProduct> imageProductsList) throws IOException {
		List<ImageDTO> imageDTOList = imageProductsList.isEmpty() ?
				List.of(createDefaultImage()) :
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

	private static ImageDTO createDefaultImage() throws IOException {
		byte[] img = downloadImageFromFileSystem();
		return new ImageDTO(
				"default-image.png",
				img.length,
				"image/png",
				img
		);
	}
	
	private static byte[] downloadImageFromFileSystem() {
		try {
			// Usa o ClassLoader para encontrar o recurso no caminho correto em tempo de execução
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			if (classLoader.getResource("images/no-image.png") == null) {
				throw new IOException("File not found: images/no-image.png");
			}

			Path filePath = Paths.get(classLoader.getResource("images/no-image.png").toURI());
			return Files.readAllBytes(filePath);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao carregar a imagem", e);
		}
	}
}
