package com.rtarcisio.inventaryms.domains;


import com.rtarcisio.inventaryms.enums.CategoryEnum;
import com.rtarcisio.inventaryms.state.ProductState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Inventory inventory;

    @Enumerated(EnumType.STRING)
    private ProductState state;

    public void checkAvailability() {
        state.checkAvailability(this);
    }

    public void notifyUsers() {
        state.notifyUsers(this);
    }
}
