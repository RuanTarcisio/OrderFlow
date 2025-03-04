package com.rtarcisio.inventaryms.services;

import com.rtarcisio.inventaryms.domains.ImageProduct;
import com.rtarcisio.inventaryms.domains.ProductGroup;
import com.rtarcisio.inventaryms.domains.SkuProduct;
import com.rtarcisio.inventaryms.dtos.ProductSimpleDTO;
import com.rtarcisio.inventaryms.dtos.ProductDetailedDTO;
import com.rtarcisio.inventaryms.dtos.SkuDetailedDTO;
import com.rtarcisio.inventaryms.dtos.SkuSimpleDTO;
import com.rtarcisio.inventaryms.dtos.input.SkuInput;
import com.rtarcisio.inventaryms.dtos.input.ProductInputSimple;
import com.rtarcisio.inventaryms.enums.CategoryEnum;
import com.rtarcisio.inventaryms.mappers.ImageMapper;
import com.rtarcisio.inventaryms.mappers.ProductMapper;
import com.rtarcisio.inventaryms.mappers.SkuMapper;
import com.rtarcisio.inventaryms.repositories.ProductRepository;
import com.rtarcisio.inventaryms.repositories.SkuProductRepository;
import com.rtarcisio.inventaryms.services.exceptions.ObjetoJaCadastradoException;
import com.rtarcisio.inventaryms.services.exceptions.ObjetoNaoEncontradoException;
import com.rtarcisio.inventaryms.utils.ProductAttributeValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final SkuProductRepository skuProductRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(SkuProductRepository skuProductRepository, ProductRepository productRepository) {
        this.skuProductRepository = skuProductRepository;
        this.productRepository = productRepository;
    }

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

//        ProductGroup productGroup = ProductMapper.inputDetailedToProduct(inputDetailed);
//        List<ImageProduct> imageProducts = getListImageProducts(inputDetailed, productGroup);

//        productGroup.getInventory().setProduct(productGroup);
//        productGroup.setState(ProductState.NEW_PRODUCT);
//        productGroup.setImageProducts(imageProducts);
//        productGroup = skuRepository.save(productGroup);
//        if (!imageProducts.isEmpty()) {
//            String mainImageId = imageProducts.get(0).getId();
//            productGroup.setMainImage_id(mainImageId);
//        }

    }

    @Transactional
    public SkuSimpleDTO saveSkuProduct(String productId, SkuInput skuInputSimple) {
        ProductGroup product = findProduct(productId);
        SkuProduct skuProduct = SkuMapper.inputToModel(skuInputSimple);
        skuProduct.setProductGroup(product);
        skuProduct.setImageProducts(ImageMapper.mapToImageList(skuInputSimple.getFiles()));

        Set<String> validAttributes = ProductAttributeValidator.getRequiredAttributes(product.getCategory());

        // Filtra apenas os atributos permitidos
        Map<String, String> filteredAttributes = skuInputSimple.getAttributes().entrySet().stream()
                .filter(entry -> validAttributes.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Atualiza os atributos no SKU
        skuInputSimple.setAttributes(filteredAttributes);

        skuProduct = skuProductRepository.save(skuProduct);
        return null;
    }

    public SkuDetailedDTO saveProductSku(SkuInput inputSimple) {
        return null;

    }

    public SkuSimpleDTO getSkuDetailed(String skuId) {
        return skuProductRepository.findSkuDetailedById(skuId)
                .orElseThrow(() -> new EntityNotFoundException("SKU não encontrado"));
    }

    public ProductSimpleDTO saveProduct(ProductInputSimple inputSimple) {
//        if (skuRepository.findByName(inputSimple.getName()).isPresent()) {
//            throw new ObjetoJaCadastradoException("");
//        }




//        Inventory inventory = new Inventory();
//        ProductGroup productGroup = ProductMapper.inputSimpleToProduct(inputSimple);
//        inventory.setProductGroup(productGroup);
////        productGroup.setState(ProductState.IN_STOCK);
////        productGroup.setInventory(inventory);
//        productGroup = skuRepository.save(productGroup);
        return null;
//        return ProductDTO.toDTO(productGroup);
    }

    public ProductDetailedDTO getProductDetailed(String id) throws IOException {
        ProductGroup productGroup = findProduct(id);
//        List<ImageProduct> imageProductsList = productGroup.getImageProducts();
//        Pr?oductDetailedDTO dto = ProductMapper.toDetailedDTO(productGroup, imageProductsList);

        return null; // dto;

    }

    public List<ProductSimpleDTO> getAllProducts() {
        List<ProductGroup> listProductGroup = productRepository.findAll();

        return listProductGroup.stream().map(product -> ProductSimpleDTO.toDTO(product)).collect(Collectors.toList());

    }

    public ProductGroup updateProduct(String id, ProductInputSimple input) {
        ProductGroup productGroup = findProduct(id);

        productGroup.setName(input.getName());
//        productGroup.setPrice(input.getPrice());
        productGroup.setCategory(CategoryEnum.valueOf(input.getCategory().toUpperCase()));

        return productGroup;
    }

    public void deleteProduct(String id) {
        ProductGroup productGroup = findProduct(id);

        productRepository.delete(productGroup);
    }

//    public List<ProductProjection> getProjectedProducts() {
//        return productRepository.findAllProjectedProducts();
//    }

    private List<ImageProduct> getListImageProducts(SkuInput inputDetailed, SkuProduct skuProduct) {

        if (inputDetailed.getFiles() == null) return Collections.emptyList();

        List<ImageProduct> list = ImageMapper.mapToImageList(inputDetailed.getFiles());
        list.forEach(item -> {
            item.setSkuProduct(skuProduct);
        });

        return list;
    }


}
