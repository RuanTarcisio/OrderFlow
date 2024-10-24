package com.rtarcisio.inventaryms.services;

import com.rtarcisio.inventaryms.dtos.input.ProductInventoryInputUpdate;
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

    public void updateStock(Long productId, Integer quantity) {
        // Atualiza o estoque e possivelmente muda o estado do produto
    }

    public void checkStockLevels(Long productId) {
        // Verifica se o produto atingiu um estado crítico
    }


    public void updateProductInventory(Long id, ProductInventoryInputUpdate update) {
    }
}
