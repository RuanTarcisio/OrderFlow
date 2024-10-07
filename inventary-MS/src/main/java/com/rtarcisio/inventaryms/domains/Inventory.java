package com.rtarcisio.inventaryms.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Long id; // Pode ser opcional se você usar productId como chave
    private Long productId;
    private Integer availableQuantity;
    private Integer minimumThreshold;
    private Integer reorderLevel;
    private LocalDateTime lastUpdated;
}
