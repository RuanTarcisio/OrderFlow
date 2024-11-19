package com.rtarcisio.inventaryms.repositories;

import com.rtarcisio.inventaryms.domains.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageProductRepository extends JpaRepository<ImageProduct, String> {
}
