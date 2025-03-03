package com.rtarcisio.inventaryms.services;

import com.rtarcisio.inventaryms.domains.ImageProduct;
import com.rtarcisio.inventaryms.domains.Inventory;
import com.rtarcisio.inventaryms.domains.ProductGroup;
import com.rtarcisio.inventaryms.dtos.ProductDTO;
import com.rtarcisio.inventaryms.dtos.ProductDetailedDTO;
import com.rtarcisio.inventaryms.dtos.input.ProductSkuInputDetailed;
import com.rtarcisio.inventaryms.dtos.input.ProductInputSimple;
import com.rtarcisio.inventaryms.enums.CategoryEnum;
import com.rtarcisio.inventaryms.mappers.ImageMapper;
import com.rtarcisio.inventaryms.mappers.ProductMapper;
import com.rtarcisio.inventaryms.repositories.ProductRepository;
import com.rtarcisio.inventaryms.repositories.SkuRepository;
import com.rtarcisio.inventaryms.services.exceptions.ObjetoJaCadastradoException;
import com.rtarcisio.inventaryms.services.exceptions.ObjetoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final SkuRepository skuRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(SkuRepository skuRepository, ProductRepository productRepository) {
        this.skuRepository = skuRepository;
        this.productRepository = productRepository;
    }

    public ProductGroup findProduct(String id) {
        return productRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("Product not found."));
    }

    @Transactional
    public ProductDTO saveSkuProduct(ProductSkuInputDetailed inputDetailed) throws IOException {
        if (productRepository.findByName(inputDetailed.getName()).isPresent()) {
            throw new ObjetoJaCadastradoException("Product already found");
        }
        ProductGroup productGroup = ProductMapper.inputSimpleToProduct(inputDetailed);



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
        return ProductDTO.toDTO(null);
    }

    public ProductDTO saveProduct(ProductInputSimple inputSimple) {
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

    public List<ProductDTO> getAllProducts() {
        List<ProductGroup> listProductGroup = productRepository.findAll();

        return listProductGroup.stream().map(product -> ProductDTO.toDTO(product)).collect(Collectors.toList());

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

    private List<ImageProduct> getListImageProducts(ProductSkuInputDetailed inputDetailed, ProductGroup productGroup) {

        if (inputDetailed.getFiles() == null) return Collections.emptyList();

        List<ImageProduct> list = ImageMapper.mapToImageList(inputDetailed.getFiles());
        list.forEach(item -> {
//            item.setProductGroup(productGroup);
        });

        return list;
    }

}
