package com.rtarcisio.inventaryms.domains;

import com.rtarcisio.inventaryms.enums.EventTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_events")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class InventoryEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_product_id", nullable = false)
    private SkuProduct skuProduct;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventTypeEnum eventType;

    @Column(nullable = false)
    private int oldTotalQuantity;

    @Column(nullable = false)
    private int newTotalQuantity;

    @Column(nullable = false)
    private int oldAvailableQuantity;

    @Column(nullable = false)
    private int newAvailableQuantity;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp;

    public InventoryEvent(SkuProduct skuProduct, EventTypeEnum eventType, int oldQuantity, int newQuantity, int oldAvailableQuantityQuantity, int newAvailableQuantity) {
        this.skuProduct = skuProduct;
        this.eventType = eventType;
        this.oldTotalQuantity = oldQuantity;
        this.newTotalQuantity = newQuantity;
        this.oldAvailableQuantity = oldAvailableQuantityQuantity;
        this.newAvailableQuantity = newAvailableQuantity;
    }
}