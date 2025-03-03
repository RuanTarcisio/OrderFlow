package com.rtarcisio.inventaryms.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SkuInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductGroup productGroup;

    private int unavailableQuantity;

    private int availableQuantity;

    private int totalQuantity;

    private int minimumThreshold;

    @OneToMany(mappedBy = "skuInventory", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ImageProduct> imageProducts;

    @OneToMany(mappedBy = "skuInventory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryEvent> inventoryEvents = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "sku_attributes", joinColumns = @JoinColumn(name = "sku_id"))
    @MapKeyColumn(name = "attribute_name")
    @Column(name = "attribute_value")
    private Map<String, String> attributes = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "price_watchers", joinColumns = @JoinColumn(name = "sku_id"))
    @Column(name = "user_id")
    private Set<String> priceWatcherUsers = new HashSet<>();

    private Boolean isAvailable;

    @CreatedDate
    private LocalDate createdDate;

    @LastModifiedDate
    private LocalDate lastUpdate;

    public Boolean isInStock() {
        return availableQuantity > 0;
    }
}
