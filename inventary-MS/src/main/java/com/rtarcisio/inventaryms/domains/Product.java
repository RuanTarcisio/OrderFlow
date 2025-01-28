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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @JoinColumn(name = "main_image_id")
    private String mainImage_id;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Inventory inventory;

    @Enumerated(EnumType.STRING)
    private ProductState state;

    @ElementCollection
    @CollectionTable(
            name = "followers_product",
            joinColumns = @JoinColumn(name = "product_id")
    )
    @Column(name = "user_id")
    private Set<String> followedProductUsers = new HashSet<>();

    private LocalDate lastCheck;

    public void checkAvailability() {
        state.checkAvailability(this);
    }

    public void notifyUsers() {
        state.notifyUsers(this);
    }
}
