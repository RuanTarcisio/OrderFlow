package com.rtarcisio.inventaryms.controllers;

import com.rtarcisio.inventaryms.domains.Product;
import com.rtarcisio.inventaryms.dtos.ProductDTO;
import com.rtarcisio.inventaryms.dtos.ProductDetailedDTO;
import com.rtarcisio.inventaryms.dtos.input.ProductInputDetailed;
import com.rtarcisio.inventaryms.dtos.input.ProductInputSimple;
import com.rtarcisio.inventaryms.dtos.input.ProductInventoryInputUpdate;
import com.rtarcisio.inventaryms.dtos.input.ProductStockUpdate;
import com.rtarcisio.inventaryms.dtos.projection.ProductProjection;
import com.rtarcisio.inventaryms.mappers.ProductMapper;
import com.rtarcisio.inventaryms.repositories.InventoryRepository;
import com.rtarcisio.inventaryms.services.InventoryService;
import com.rtarcisio.inventaryms.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
@Validated
public class ProductController {

    private final ProductService productService;
    private final InventoryService inventoryService;

    @Autowired
    public ProductController(ProductService productService, InventoryService inventoryService) {
        this.productService = productService;
        this.inventoryService = inventoryService;
    }

    @PostMapping(value = "/register/detailed", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveProductDetailed(@ModelAttribute @Valid ProductInputDetailed inputDetailed) throws IOException {

        ProductDTO dto = productService.saveProduct(inputDetailed);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/register/simple")
    public ResponseEntity<Void> saveProductSimple(@Valid ProductInputSimple inputSimple) throws IOException {

        ProductDTO dto = productService.saveProduct(inputSimple);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailedDTO> getProduct(@PathVariable Long id) throws IOException {
        ProductDetailedDTO productDTO = productService.getProductDetailed(id);

        return ResponseEntity.ok().body(productDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {

        List<ProductDTO> allProducts = productService.getAllProducts();
        return ResponseEntity.ok().body(allProducts);
    }

    @GetMapping("/all-products/")
    public List<ProductProjection> getProductsByCategory() {
        return productService.getProjectedProducts();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, ProductInputSimple input) {

        Product product = productService.updateProduct(id, input);

        return ResponseEntity.ok().body(ProductMapper.productToDTO(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}/inventory")
    public ResponseEntity<Void> updateInventory(@PathVariable Long id, @RequestBody ProductInventoryInputUpdate update) {
        inventoryService.updateProductInventory(id, update);

        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<Void> updateStock(@PathVariable Long id, @RequestBody ProductStockUpdate update) {
        inventoryService.updateStock(id, update);

        return ResponseEntity.accepted().build();
    }



}
