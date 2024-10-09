package com.rtarcisio.inventaryms.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
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
    private Product product; // Referência ao produto

    private Integer availableQuantity;
    private Integer minimumThreshold;
    private Integer reorderLevel;
    private LocalDateTime lastUpdated;
}
