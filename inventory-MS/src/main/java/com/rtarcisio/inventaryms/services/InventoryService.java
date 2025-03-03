package com.rtarcisio.inventaryms.services;

import com.rtarcisio.inventaryms.domains.Inventory;
import com.rtarcisio.inventaryms.domains.ProductGroup;
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

//    public Integer updateStock(Long productId, Integer value) {
//        ProductGroup productGroup = productService.findProduct(productId);
//        Inventory inventory = productGroup.getInventory();
//        inventory.updateStock(value);
//
//        productGroup.checkAvailability();
//        inventory.setLastUpdated(LocalDateTime.now());
//        return inventory.getAvailableQuantity();
//    }
//
//    public void checkStockLevels(Long productId) {
//        ProductGroup productGroup = productService.findProduct(productId);
//        Inventory inventory = productGroup.getInventory();
//        productGroup.checkAvailability();
//    }
//
//
//    public void updateProductInventory(Long id, ProductInventoryInputUpdate update) {
//        ProductGroup productGroup = productService.findProduct(id);
//        Inventory inventory = productGroup.getInventory();
//
//        inventory.setAvailableQuantity(update.minimumThreshold());
//        inventory.setMinimumThreshold(update.minimumThreshold());
//        inventory.updateStock(update.quantity());
//
//        inventory.setLastUpdated(LocalDateTime.now());
//        productGroup.checkAvailability();
//    }

    public Boolean hasProductInStock(Long idProduct) {

//        Inventory inventory = inventoryRepository.findByProduct_Id(idProduct).orElseThrow(()-> new RuntimeException(""));
//        return inventory.isInStock();
        return null;
    }
}
