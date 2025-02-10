package com.rtarcisio.inventaryms.dtos;

import com.rtarcisio.inventaryms.domains.Product;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @NotEmpty private Long id;
    @NotEmpty private String name;
    @NotEmpty private String description; 
    @NotEmpty private BigDecimal price;
    @NotEmpty private String category;
    @NotEmpty private Integer availableQuantity;
    @NotEmpty private Integer minimumThreshold;
    @NotEmpty private Integer reorderLevel;


    public static ProductDTO toDTO(Product product){
        ProductDTO dto = new ProductDTO();
        return dto.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory().name())
                .description(product.getDescription())
                .price(product.getPrice())
                .availableQuantity(product.getInventory().getAvailableQuantity())
//                .minimumThreshold(product.getInventory().getMinimumThreshold())
//                .reorderLevel(product.getInventory().getReorderLevel())
                .build();
    }
}
