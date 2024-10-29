package com.rtarcisio.inventaryms.repositories;

import com.rtarcisio.inventaryms.domains.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, String> {
}
