package com.rtarcisio.inventaryms.domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "sku_products")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor

public class SkuProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_group_id", nullable = false)
    private ProductGroup productGroup;

    @Column(nullable = false)
    private int availableQuantity = 0;

    @Column(nullable = false)
    private int totalQuantity = 0;
    @Column(nullable = false)
    private int minimumThreshold = 0;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @OneToMany(mappedBy = "skuProduct", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ImageProduct> imagesProduct = new ArrayList<>();

    @OneToMany(mappedBy = "skuProduct", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<InventoryEvent> inventoryEvents = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "sku_attributes", joinColumns = @JoinColumn(name = "sku_product_id"))
    @MapKeyColumn(name = "attribute_name")
    @Column(name = "attribute_value")
    private Map<String, String> attributes = new HashMap<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "price_watchers", joinColumns = @JoinColumn(name = "sku_product_id"))
    @Column(name = "user_id")
    private Set<String> priceWatcherUsers = new HashSet<>(); // Inicializar

    @Column(nullable = false)
    private Boolean isAvailable;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate createdDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDate lastUpdate;

    public boolean isInStock() {
        return this.availableQuantity > 0;
    }

    public boolean makeReserve(int quantityToReserve) {
        if (quantityToReserve <= 0) {
            throw new IllegalArgumentException("Quantity to reserve must be positive.");
        }
        if (this.availableQuantity >= quantityToReserve) {
            this.availableQuantity -= quantityToReserve;
            return true;
        }
        return false;
    }

    public boolean releaseReserved(int quantityToRelease) {
        if (quantityToRelease <= 0) {
            throw new IllegalArgumentException("Quantity to release must be positive.");
        }

        this.availableQuantity += quantityToRelease;
        return true;

    }

    public void addStock(int quantityToAdd) {
        if (quantityToAdd <= 0) {
            throw new IllegalArgumentException("Quantity to add must be positive.");
        }
        this.availableQuantity += quantityToAdd;
        this.totalQuantity += quantityToAdd;
    }

    public boolean removeStock(int quantityToRemove) {
        if (quantityToRemove <= 0) {
            throw new IllegalArgumentException("Quantity to remove must be positive.");
        }
        if (this.availableQuantity >= quantityToRemove) {
            this.availableQuantity -= quantityToRemove;
            this.totalQuantity -= quantityToRemove;
            return true;
        }
        return false;
    }

    public void addImageProduct(ImageProduct image) {
        this.imagesProduct.add(image);
        image.setSkuProduct(this);
    }

    public void removeImageProduct(ImageProduct image) {
        this.imagesProduct.remove(image);
        image.setSkuProduct(null);
    }

    public void addInventoryEvent(InventoryEvent event) {
        this.inventoryEvents.add(event);
        event.setSkuProduct(this);
    }

    public void removeInventoryEvent(InventoryEvent event) {
        this.inventoryEvents.remove(event);
        event.setSkuProduct(null);
    }
}