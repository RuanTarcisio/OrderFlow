package com.rtarcisio.inventaryms.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailedDTO {

    @NotEmpty private String id;
    @NotEmpty private String name;
    @NotEmpty private String description; 
    @NotEmpty private BigDecimal price;
    @NotEmpty private String category;
    @NotEmpty private Integer availableQuantity;
    @NotEmpty private Integer minimumThreshold;
    @NotEmpty private Integer reorderLevel;
    @NotEmpty private List<ImageDTO> files;

//    @NotEmpty private List<MultipartFile> files;

}
