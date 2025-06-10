package com.rtarcisio.inventaryms.repositories;

import com.rtarcisio.inventaryms.domains.SkuProduct;
import com.rtarcisio.inventaryms.dtos.SkuSimpleDTO;
import com.rtarcisio.inventaryms.enums.CategoryEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SkuProductRepository extends JpaRepository<SkuProduct, String> {

//    Optional<ProductGroup> findByName(String name);
//
//    List<ProductGroup> findAllByCategory(CategoryEnum category);
//
//    List<ProductGroup> findAllByNameIsContainingIgnoreCase(String category);
//
//    @Query("SELECT p.id AS id, p.name AS name, p.price AS price, p.category AS category, p.description AS description, i.id AS main_Image_Id FROM ProductGroup p LEFT JOIN ImageProduct i ON p.mainImage_id = i.id")
//    List<ProductProjection> findAllProjectedProducts();

    @Query("""
        SELECT new com.rtarcisio.inventaryms.dtos.SkuSimpleDTO(
            s.id,
            p.id,
            s.availableQuantity,
            p.name,
            p.description,
            s.price,
            p.category
        )
        FROM SkuProduct s
        JOIN s.productGroup p
        WHERE s.id = :skuId
    """)
    Optional<SkuSimpleDTO> findSkuDetailedById(@Param("skuId") String skuId);
//    CAST(s.attributes AS string),


//    @Query("""
//    SELECT new com.rtarcisio.inventaryms.dtos.SkuSimpleDTO(
//        s.id,
//        p.id,
//        s.availableQuantity,
//        s.attributes,
//        p.name,
//        p.description,
//        s.price,
//        p.category
//    )
//    FROM SkuProduct s
//    JOIN s.productGroup p
//    WHERE (:category IS NULL OR p.category = :category)
//    AND (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
//""")
//    Page<SkuSimpleDTO> findByCategoryAndName(
//            @Param("category") CategoryEnum category,
//            @Param("name") String name,
//            Pageable pageable
//    );

}
