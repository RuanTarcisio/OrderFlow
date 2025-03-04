package com.rtarcisio.inventaryms.domains;

import com.rtarcisio.inventaryms.enums.EventTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sku_id", nullable = false)
    private SkuProduct skuProduct;

    private EventTypeEnum eventType;

    private int oldQuantity;

    private int newQuantity;

    @LastModifiedDate
    private LocalDateTime timestamp;


}
