package com.rtarcisio.inventaryms.services;

import com.rtarcisio.inventaryms.domains.ImageProduct;
import com.rtarcisio.inventaryms.domains.InventoryEvent;
import com.rtarcisio.inventaryms.domains.ProductGroup;
import com.rtarcisio.inventaryms.domains.SkuProduct;
import com.rtarcisio.inventaryms.dtos.ProductDetailedDTO;
import com.rtarcisio.inventaryms.dtos.ProductSimpleDTO;
import com.rtarcisio.inventaryms.dtos.SkuDetailedDTO;
import com.rtarcisio.inventaryms.dtos.SkuSimpleDTO;
import com.rtarcisio.inventaryms.dtos.input.ProductInputSimple;
import com.rtarcisio.inventaryms.dtos.input.SkuInput;
import com.rtarcisio.inventaryms.enums.CategoryEnum;
import com.rtarcisio.inventaryms.enums.EventTypeEnum;
import com.rtarcisio.inventaryms.mappers.ImageMapper;
import com.rtarcisio.inventaryms.mappers.ProductMapper;
import com.rtarcisio.inventaryms.mappers.SkuMapper;
import com.rtarcisio.inventaryms.repositories.InventoryEventRepository;
import com.rtarcisio.inventaryms.repositories.ProductRepository;
import com.rtarcisio.inventaryms.repositories.SkuProductRepository;
import com.rtarcisio.inventaryms.services.exceptions.ConflictException;
import com.rtarcisio.inventaryms.services.exceptions.ObjetoJaCadastradoException;
import com.rtarcisio.inventaryms.services.exceptions.ObjetoNaoEncontradoException;
import com.rtarcisio.inventaryms.utils.ProductAttributeValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final SkuProductRepository skuProductRepository;
    private final ProductRepository productRepository;
    private final InventoryEventRepository inventoryEventRepository;

    public ProductGroup findProduct(String id) {
        return productRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("Product not found."));
    }

    public ProductSimpleDTO saveProductSimple(ProductInputSimple inputDetailed) {

        if (productRepository.findByName(inputDetailed.getName()).isPresent()) {
            throw new ObjetoJaCadastradoException("Product already found");
        }

        ProductGroup productGroup = ProductMapper.inputSimpleToProduct(inputDetailed);
        productGroup = productRepository.save(productGroup);
        return ProductSimpleDTO.toDTO(productGroup);
    }


    @Transactional
    public SkuSimpleDTO saveSkuProduct(String productId, SkuInput skuInputSimple) {

        ProductGroup product = findProduct(productId);
        SkuProduct skuProduct = SkuMapper.inputToModel(skuInputSimple);
        skuProduct.setProductGroup(product);
        skuProduct.setImagesProduct(ImageMapper.mapToImageList(skuInputSimple.getFiles()));
        Set<String> validAttributes = ProductAttributeValidator.getRequiredAttributes(product.getCategory());
        // if(skuInputSimple.getAttributes() != null) {
        // Map<String, String> filteredAttributes = skuInputSimple.getAttributes().entrySet().stream()
        // .filter(entry -> validAttributes.contains(entry.getKey()))
        // .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        // Atualiza os atributos no SKU
        // skuInputSimple.setAttributes(filteredAttributes);

// }
        skuProduct = skuProductRepository.save(skuProduct);
        return SkuMapper.modelToSimpleDto(skuProduct);
    }

    public SkuDetailedDTO saveProductSku(SkuInput inputSimple) {
        return null;

    }

    public SkuSimpleDTO getProductSkuSimple(String skuId) {

        return skuProductRepository.findSkuDetailedById(skuId).orElseThrow(() -> new EntityNotFoundException("SKU não encontrado"));

    }

    public ProductSimpleDTO saveProduct(ProductInputSimple inputSimple) {

// if (skuRepository.findByName(inputSimple.getName()).isPresent()) {

// throw new ObjetoJaCadastradoException("");

// }


// Inventory inventory = new Inventory();

// ProductGroup productGroup = ProductMapper.inputSimpleToProduct(inputSimple);

// inventory.setProductGroup(productGroup);

//// productGroup.setState(ProductState.IN_STOCK);

//// productGroup.setInventory(inventory);

// productGroup = skuRepository.save(productGroup);
        return null;
// return ProductDTO.toDTO(productGroup);
    }

    public ProductDetailedDTO getProductDetailed(String id) throws IOException {
        ProductGroup productGroup = findProduct(id);
// List<ImageProduct> imageProductsList = productGroup.getImageProducts();

// Pr?oductDetailedDTO dto = ProductMapper.toDetailedDTO(productGroup, imageProductsList);
        return null; // dto;
    }

    public List<ProductSimpleDTO> getAllProducts() {

        List<ProductGroup> listProductGroup = productRepository.findAll();
        return listProductGroup.stream().map(product -> ProductSimpleDTO.toDTO(product)).collect(Collectors.toList());
    }

    public ProductGroup updateProduct(String id, ProductInputSimple input) {
        ProductGroup productGroup = findProduct(id);
        productGroup.setName(input.getName());

// productGroup.setPrice(input.getPrice());
        productGroup.setCategory(CategoryEnum.valueOf(input.getCategory().toUpperCase()));
        return productGroup;
    }

    public void deleteProduct(String id) {
        ProductGroup productGroup = findProduct(id);
        productRepository.delete(productGroup);
    }

    private List<ImageProduct> getListImageProducts(SkuInput inputDetailed, SkuProduct skuProduct) {

        if (inputDetailed.getFiles() == null) return Collections.emptyList();

        List<ImageProduct> list = ImageMapper.mapToImageList(inputDetailed.getFiles());
        list.forEach(item -> {
            item.setSkuProduct(skuProduct);
        });
        return list;
    }

    @Transactional
    public void reserveSkuProduct(String skuProductId, int quantity) {
        SkuProduct skuProduct = skuProductRepository.findById(skuProductId)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("SKU not found."));

        int oldAvailableQuantity = skuProduct.getAvailableQuantity();
        int oldTotalQuantity = skuProduct.getTotalQuantity();
        boolean reserved = skuProduct.makeReserve(quantity);
        if (!reserved) {
            throw new ConflictException("Insufficient available stock to reserve " + quantity + " units for SKU " + skuProductId);
        }
        skuProductRepository.save(skuProduct);
        int newAvailableQuantity = skuProduct.getAvailableQuantity();
        int newTotalQuantity = skuProduct.getTotalQuantity();

        InventoryEvent event = new InventoryEvent(
                skuProduct,
                EventTypeEnum.RESERVATION_MADE,
                oldTotalQuantity,
                newTotalQuantity,
                oldAvailableQuantity,
                newAvailableQuantity
        );
        inventoryEventRepository.save(event);
    }

}