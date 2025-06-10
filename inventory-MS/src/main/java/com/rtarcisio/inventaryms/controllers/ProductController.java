package com.rtarcisio.inventaryms.controllers;

import com.rtarcisio.inventaryms.domains.ProductGroup;
import com.rtarcisio.inventaryms.dtos.ProductDetailedDTO;
import com.rtarcisio.inventaryms.dtos.ProductSimpleDTO;
import com.rtarcisio.inventaryms.dtos.SkuSimpleDTO;
import com.rtarcisio.inventaryms.dtos.input.ProductInputSimple;
import com.rtarcisio.inventaryms.dtos.input.ProductInventoryInputUpdate;
import com.rtarcisio.inventaryms.dtos.input.SkuInput;
import com.rtarcisio.inventaryms.enums.CategoryEnum;
import com.rtarcisio.inventaryms.mappers.ProductMapper;
import com.rtarcisio.inventaryms.services.ProductService;
import com.rtarcisio.inventaryms.utils.ProductAttributeValidator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @PostMapping(value = "/register/detailed", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<Void> saveProductDetailed(@ModelAttribute @Valid ProductSkuInputDetailed inputDetailed) throws IOException {
//
//        ProductDTO dto = productService.saveProduct(inputDetailed);
//
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
//        return ResponseEntity.created(uri).build();
//    }

    @PostMapping("/register")
    public ResponseEntity<Void> saveProductSimple(@Valid @RequestBody ProductInputSimple inputSimple) {
        ProductAttributeValidator.getRequiredAttributes(CategoryEnum.valueOf(inputSimple.getCategory().toUpperCase()));
        ProductSimpleDTO dto = productService.saveProductSimple(inputSimple);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping(value = "/register/{productId}/sku", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveSkuSimple(@PathVariable String productId,
                                              @ModelAttribute SkuInput skuInput
                                             /* @RequestParam("price") BigDecimal price,
                                              @RequestParam("minimumThreshold") int minimumThreshold,
                                              @RequestParam("totalQuantity") int totalQuantity,
                                              @RequestParam Map<String, String> attributes, // 🔥 Captura os atributos individuais
                                              @RequestParam("files") List<MultipartFile> files*/) throws IOException {

//        SkuInput skuInput = new SkuInput();
//        skuInput.setMinimumThreshold(minimumThreshold);
//        skuInput.setPrice(price);
//        skuInput.setTotalQuantity(totalQuantity);
//        skuInput.setAttributes(attributes);
//        skuInput.setFiles(files);
//        skuInput.setAttributes(attributes);

        SkuSimpleDTO dto = productService.saveSkuProduct(productId, skuInput);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getSkuId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailedDTO> getProduct(@PathVariable String id) throws IOException {
        ProductDetailedDTO productDTO = productService.getProductDetailed(id);

        return ResponseEntity.ok().body(productDTO);
    }

    @GetMapping("/sku/{id}")
    public ResponseEntity<SkuSimpleDTO> getSkuProduct(@PathVariable String id) throws IOException {
        SkuSimpleDTO skuProductDTO = productService.getProductSkuSimple(id);

        return ResponseEntity.ok().body(skuProductDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductSimpleDTO>> getAllProducts() {

        List<ProductSimpleDTO> allProducts = productService.getAllProducts();
        return ResponseEntity.ok().body(allProducts);
    }

    @PostMapping("/{skuProductId}/reserve-stock")
    public ResponseEntity<Void> reserveSkuProduct(
            @PathVariable String skuProductId,
            @RequestParam int quantity) {

        productService.reserveSkuProduct(skuProductId, quantity);
        return ResponseEntity.accepted().build();
    }

//    @GetMapping("/all-products/")
//    public List<ProductProjection> getProductsByCategory() {
//        return productService.getProjectedProducts();
//    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductSimpleDTO> updateProduct(@PathVariable String id, @Valid ProductInputSimple input) {

        ProductGroup productGroup = productService.updateProduct(id, input);

        return ResponseEntity.ok().body(ProductMapper.productToDTO(productGroup));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);

        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}/inventory")
    public ResponseEntity<Void> updateInventory(@PathVariable Long id, @Valid @RequestBody ProductInventoryInputUpdate update) {
//        inventoryService.updateProductInventory(id, update);

        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<Integer> updateStock(@PathVariable Long id, @Valid @NotNull Integer update) {

        return null;
//        return ResponseEntity.accepted().body(inventoryService.updateStock(id, update));
    }

    @PutMapping("/{id}/check-stock")
    public ResponseEntity<Void> checkStock(@PathVariable Long id) {
//        inventoryService.checkStockLevels(id);

        return ResponseEntity.accepted().build();
    }

//    @PutMapping("/follow")
//    public ResponseEntity<Void> followProduct(@Valid @RequestBody FollowProduct following) {
//
//        productService.followProduct(following);
//        return ResponseEntity.accepted().build();
//    }


}
