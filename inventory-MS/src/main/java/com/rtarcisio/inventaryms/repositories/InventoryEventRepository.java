package com.rtarcisio.inventaryms.repositories;

import com.rtarcisio.inventaryms.domains.InventoryEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryEventRepository  extends JpaRepository<InventoryEvent, String> {
}
