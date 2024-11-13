package com.rtarcisio.inventaryms.services;

import com.rtarcisio.inventaryms.domains.Inventory;
import com.rtarcisio.inventaryms.domains.Product;
import com.rtarcisio.inventaryms.dtos.input.ProductInventoryInputUpdate;
import com.rtarcisio.inventaryms.dtos.input.ProductStockUpdate;
import com.rtarcisio.inventaryms.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductService productService;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, ProductService productService) {
        this.inventoryRepository = inventoryRepository;
        this.productService = productService;
    }

    public Integer updateStock(Long productId, ProductStockUpdate update) {
        Product product = productService.findProduct(productId);
        Inventory inventory = product.getInventory();
        int qtdNow = inventory.getInStock() + update.value();
        inventory.setInStock(qtdNow);

        product.setInventory(inventory);
        return qtdNow;
    }

    public void checkStockLevels(Long productId) {
        // Verifica se o produto atingiu um estado crítico
    }


    public void updateProductInventory(Long id, ProductInventoryInputUpdate update) {
        Product product = productService.findProduct(id);
        Inventory inventory = product.getInventory();

        inventory.setAvailableQuantity(update.minimumThreshold());
        inventory.setMinimumThreshold(update.minimumThreshold());
        inventory.setAvailableQuantity(inventory.getAvailableQuantity() + update.quantity());

        product.setInventory(inventory);
    }
}
