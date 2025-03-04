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
public class ProductSimpleDTO {

    @NotEmpty private String id;
    @NotEmpty private String name;
    @NotEmpty private String description; 
    @NotEmpty private String category;

    public static ProductSimpleDTO toDTO(ProductGroup productGroup){
        ProductSimpleDTO dto = new ProductSimpleDTO();
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
