    package com.rtarcisio.inventaryms.controllers;

    import com.rtarcisio.inventaryms.dtos.input.ProductInventoryInputUpdate;
    import com.rtarcisio.inventaryms.repositories.ProductRepository;
    import com.rtarcisio.inventaryms.services.InventoryService;
    import com.rtarcisio.inventaryms.services.ProductService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.validation.annotation.Validated;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/inventory")
    @Validated
    public class InventoryController {

        private final InventoryService inventoryService;
        private final ProductRepository productRepository;

        @Autowired
        public InventoryController(InventoryService inventoryService, ProductRepository productRepository) {
            this.inventoryService = inventoryService;
            this.productRepository = productRepository;
        }

        @PostMapping("/{id}/inventory")
        public ResponseEntity<Void> updateInventory(@PathVariable Long id, @RequestBody ProductInventoryInputUpdate update) {
            inventoryService.updateProductInventory(id, update);

            return ResponseEntity.accepted().build();
        }

        @PostMapping("/{productId}")
        public ResponseEntity<Void> updateStock(@PathVariable Long productId, @RequestBody Integer quantity) {
            inventoryService.updateStock(productId, quantity);

            return ResponseEntity.accepted().build();
        }

    }
