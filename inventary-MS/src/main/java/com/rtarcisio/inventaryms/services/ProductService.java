package com.rtarcisio.inventaryms.services;

import com.rtarcisio.inventaryms.domains.ImageProduct;
import com.rtarcisio.inventaryms.domains.Inventory;
import com.rtarcisio.inventaryms.domains.Product;
import com.rtarcisio.inventaryms.dtos.ProductDTO;
import com.rtarcisio.inventaryms.dtos.ProductDetailedDTO;
import com.rtarcisio.inventaryms.dtos.input.FollowProduct;
import com.rtarcisio.inventaryms.dtos.input.ProductInputDetailed;
import com.rtarcisio.inventaryms.dtos.input.ProductInputSimple;
import com.rtarcisio.inventaryms.enums.CategoryEnum;
import com.rtarcisio.inventaryms.mappers.ImageMapper;
import com.rtarcisio.inventaryms.mappers.ProductMapper;
import com.rtarcisio.inventaryms.repositories.ProductRepository;
import com.rtarcisio.inventaryms.services.exceptions.ObjetoJaCadastradoException;
import com.rtarcisio.inventaryms.services.exceptions.ObjetoNaoEncontradoException;
import com.rtarcisio.inventaryms.state.ProductState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("Product not found."));
    }

    @Transactional
    public ProductDTO saveProduct(ProductInputDetailed inputDetailed) throws IOException {
        if (productRepository.findByName(inputDetailed.getName()).isPresent()) {
            throw new ObjetoJaCadastradoException("Product already found");
        }
        Product product = ProductMapper.inputDetailedToProduct(inputDetailed);
        List<ImageProduct> imageProducts = getListImageProducts(inputDetailed, product);

        product.getInventory().setProduct(product);
        product.setState(ProductState.NEW_PRODUCT);
        product.setImageProducts(imageProducts);
        product = productRepository.save(product);
        if (!imageProducts.isEmpty()) {
            String mainImageId = imageProducts.get(0).getId();
            product.setMainImage_id(mainImageId);
        }
        return ProductDTO.toDTO(product);
    }

    public ProductDTO saveProduct(ProductInputSimple inputSimple) {
        if (productRepository.findByName(inputSimple.getName()).isPresent()) {
            throw new ObjetoJaCadastradoException("");
        }
        Inventory inventory = new Inventory();
        Product product = ProductMapper.inputSimpleToProduct(inputSimple);
        inventory.setProduct(product);
        product.setState(ProductState.IN_STOCK);
        product.setInventory(inventory);
        product = productRepository.save(product);

        return ProductDTO.toDTO(product);
    }

    public ProductDetailedDTO getProductDetailed(Long id) throws IOException {
        Product product = findProduct(id);
        List<ImageProduct> imageProductsList = product.getImageProducts();
        ProductDetailedDTO dto = ProductMapper.toDetailedDTO(product, imageProductsList);

        return dto;

    }

    public List<ProductDTO> getAllProducts() {
        List<Product> listProduct = productRepository.findAll();

        return listProduct.stream().map(product -> ProductDTO.toDTO(product)).collect(Collectors.toList());

    }

    public Product updateProduct(Long id, ProductInputSimple input) {
        Product product = findProduct(id);

        product.setName(input.getName());
        product.setPrice(input.getPrice());
        product.setCategory(CategoryEnum.valueOf(input.getCategory().toUpperCase()));

        return product;
    }

    public void deleteProduct(Long id) {
        Product product = findProduct(id);

        productRepository.delete(product);
    }

//    public List<ProductProjection> getProjectedProducts() {
//        return productRepository.findAllProjectedProducts();
//    }

    private List<ImageProduct> getListImageProducts(ProductInputDetailed inputDetailed, Product product) {

        if (inputDetailed.getFiles() == null) return Collections.emptyList();

        List<ImageProduct> list = ImageMapper.mapToImageList(inputDetailed.getFiles());
        list.forEach(item -> {
            item.setProduct(product);
        });

        return list;
    }

    public void followProduct(FollowProduct toFollow) {
        Product product = findProduct(toFollow.idProduct());
        product.getFollowedProductUsers().add(toFollow.userEmail());
        productRepository.save(product);
    }
}
