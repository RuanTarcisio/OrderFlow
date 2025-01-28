package com.rtarcisio.inventaryms.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer inStock;
    private Integer availableQuantity;
    private Integer minimumThreshold;
    private Integer reorderLevel;

    @LastModifiedDate
    private LocalDateTime lastUpdated;

    @LastModifiedBy
    private String modifiedBy;

    public void updateStock(int update){
        this.inStock += update;
        this.availableQuantity += update;
    }

    public Boolean isInStock() {
        return inStock > 0;
    }
}
