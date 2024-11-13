package com.rtarcisio.inventaryms.mappers;

import com.rtarcisio.inventaryms.domains.ImageProduct;
import com.rtarcisio.inventaryms.domains.Inventory;
import com.rtarcisio.inventaryms.domains.Product;
import com.rtarcisio.inventaryms.dtos.ImageDTO;
import com.rtarcisio.inventaryms.dtos.ProductDTO;
import com.rtarcisio.inventaryms.dtos.ProductDetailedDTO;
import com.rtarcisio.inventaryms.dtos.input.ProductInputDetailed;
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

	public static Product inputDetailedToProduct(ProductInputDetailed input) throws IOException {
		Inventory inventory = Inventory.builder()
				.reorderLevel(input.getReorderLevel())
				.minimumThreshold(input.getMinimumThreshold())
				.availableQuantity(input.getAvailableQuantity())
				.build();

		return buildProduct(input.getName(), input.getCategory(), input.getPrice(), input.getDescription(), inventory);
	}

	public static Product inputSimpleToProduct(ProductInputSimple input) {
		return buildProduct(input.getName(), input.getCategory(), input.getPrice(), input.getDescription(), null);
	}

	private static Product buildProduct(String name, String category, BigDecimal price, String description, Inventory inventory) {
		return Product.builder()
				.name(name)
				.category(CategoryEnum.valueOf(category.toUpperCase()))
				.price(price)
				.description(description)
				.inventory(inventory)
				.build();
	}

	public static ProductDTO productToDTO(Product product){

		return null;
	}


	public static ProductDetailedDTO toDetailedDTO(Product product, List<ImageProduct> imageProductsList) throws IOException {
		List<ImageDTO> imageDTOList = imageProductsList.isEmpty() ?
				List.of(createDefaultImage()) :
				ImageMapper.mapToImageDTOList(ImageMapper.mapToMultipartFileList(imageProductsList));

		return buildProductDetailedDTO(product, imageDTOList);
	}

	private static ProductDetailedDTO buildProductDetailedDTO(Product product, List<ImageDTO> imageDTOList) {
		return ProductDetailedDTO.builder()
				.id(product.getId())
				.name(product.getName())
				.category(product.getCategory().name())
				.files(imageDTOList)
				.description(product.getDescription())
				.price(product.getPrice())
				.availableQuantity(product.getInventory().getAvailableQuantity())
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
