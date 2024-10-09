package com.rtarcisio.inventaryms.services;

import com.rtarcisio.inventaryms.domains.Product;
import com.rtarcisio.inventaryms.dtos.ProductDTO;
import com.rtarcisio.inventaryms.dtos.input.ProductInput;
import com.rtarcisio.inventaryms.repositories.ProductRepository;
import com.rtarcisio.inventaryms.services.exceptions.ObjetoJaCadastradoException;
import com.rtarcisio.inventaryms.services.exceptions.ObjetoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductDTO saveProduct(ProductInput productInput) {
        if(productRepository.findByName(productInput.name()).isPresent()){
            throw new ObjetoJaCadastradoException("");
        }
        return null;
    }

    public Product findProduct(Long id){
        return productRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("Product not found."));
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> listProduct = productRepository.findAll();
        return null;
    }

    public void updateProduct(Long id) {
    }


}
