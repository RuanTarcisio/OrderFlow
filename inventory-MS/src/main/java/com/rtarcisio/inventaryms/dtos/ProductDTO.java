package com.rtarcisio.inventaryms.dtos;

import com.rtarcisio.inventaryms.domains.ProductGroup;
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

    @NotEmpty private String id;
    @NotEmpty private String name;
    @NotEmpty private String description; 
    @NotEmpty private BigDecimal price;
    @NotEmpty private String category;
    @NotEmpty private Integer availableQuantity;
    @NotEmpty private Integer minimumThreshold;
    @NotEmpty private Integer reorderLevel;


    public static ProductDTO toDTO(ProductGroup productGroup){
        ProductDTO dto = new ProductDTO();
        return dto.builder()
                .id(productGroup.getId())
                .name(productGroup.getName())
                .category(productGroup.getCategory().name())
//                .description(productGroup.getDescription())
//                .price(productGroup.getPrice())
//                .availableQuantity(productGroup.getInventory().getAvailableQuantity())
//                .minimumThreshold(product.getInventory().getMinimumThreshold())
//                .reorderLevel(product.getInventory().getReorderLevel())
                .build();
    }
}
