package com.rtarcisio.orderms.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(name = "products_cart")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOrder> products = new ArrayList<>();
}
