package com.rtarcisio.inventaryms.repositories;

import com.rtarcisio.inventaryms.domains.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

//    Optional<Inventory> findByProduct_Id(Long aLong);
}
