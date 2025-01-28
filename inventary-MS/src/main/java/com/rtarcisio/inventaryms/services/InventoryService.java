package com.rtarcisio.inventaryms.services;

import com.rtarcisio.inventaryms.domains.Inventory;
import com.rtarcisio.inventaryms.domains.Product;
import com.rtarcisio.inventaryms.dtos.input.ProductInventoryInputUpdate;
import com.rtarcisio.inventaryms.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductService productService;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, ProductService productService) {
        this.inventoryRepository = inventoryRepository;
        this.productService = productService;
    }

    public Integer updateStock(Long productId, Integer value) {
        Product product = productService.findProduct(productId);
        Inventory inventory = product.getInventory();
        inventory.updateStock(value);

        product.checkAvailability();
        inventory.setLastUpdated(LocalDateTime.now());
        return inventory.getAvailableQuantity();
    }

    public void checkStockLevels(Long productId) {
        Product product = productService.findProduct(productId);
        Inventory inventory = product.getInventory();
        product.checkAvailability();
    }


    public void updateProductInventory(Long id, ProductInventoryInputUpdate update) {
        Product product = productService.findProduct(id);
        Inventory inventory = product.getInventory();

        inventory.setAvailableQuantity(update.minimumThreshold());
        inventory.setMinimumThreshold(update.minimumThreshold());
        inventory.updateStock(update.quantity());

        inventory.setLastUpdated(LocalDateTime.now());
        product.checkAvailability();
    }

    public Boolean hasProductInStock(Long idProduct) {

        Inventory inventory = inventoryRepository.findByProduct_Id(idProduct).orElseThrow(()-> new RuntimeException(""));
        return inventory.isInStock();
    }
}
