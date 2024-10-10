package com.rtarcisio.inventaryms.services;

import com.rtarcisio.inventaryms.domains.Product;
import com.rtarcisio.inventaryms.dtos.ProductDTO;
import com.rtarcisio.inventaryms.dtos.input.ProductInputDetailed;
import com.rtarcisio.inventaryms.dtos.input.ProductInputSimple;
import com.rtarcisio.inventaryms.enums.CategoryEnum;
import com.rtarcisio.inventaryms.mappers.ProductMapper;
import com.rtarcisio.inventaryms.repositories.InventoryRepository;
import com.rtarcisio.inventaryms.repositories.ProductRepository;
import com.rtarcisio.inventaryms.services.exceptions.ObjetoJaCadastradoException;
import com.rtarcisio.inventaryms.services.exceptions.ObjetoNaoEncontradoException;
import com.rtarcisio.inventaryms.state.ProductState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    public ProductDTO saveProduct(ProductInputDetailed productInput) {
        if(productRepository.findByName(productInput.getName()).isPresent()){
            throw new ObjetoJaCadastradoException("");
        }

        Product product = ProductMapper.inputToProduct(productInput);
        product.setState(ProductState.IN_STOCK);
        product = productRepository.save(product);

        return ProductDTO.toDTO(product);

    }

    public Product findProduct(Long id){
        return productRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("Product not found."));
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> listProduct = productRepository.findAll();

        return listProduct.stream()
                .map(product -> ProductDTO.toDTO(product))
                .collect(Collectors.toList());

    }

    public void updateProduct(Long id, ProductInputSimple input) {
        Product product = findProduct(id);

        product.setName(input.getName());
        product.setPrice(input.getPrice());
        product.setCategory(CategoryEnum.valueOf(input.getCategory().toUpperCase()));
    }


}
