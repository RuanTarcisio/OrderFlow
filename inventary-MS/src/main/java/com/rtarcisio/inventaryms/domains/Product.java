package com.rtarcisio.inventaryms.domains;


import com.rtarcisio.inventaryms.enums.CategoryEnum;
import com.rtarcisio.inventaryms.state.ProductState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ImageProduct> imageProducts;

//    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_image_id") // Mapeando a coluna de chave estrangeira
    private String mainImage_id; // Relacionamento com ImageProduct

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
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
