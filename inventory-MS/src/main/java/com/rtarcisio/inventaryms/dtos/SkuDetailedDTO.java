package com.rtarcisio.inventaryms.dtos;

import com.rtarcisio.inventaryms.domains.ProductGroup;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkuDetailedDTO {

    private String skuId;
    private int productId;
    private int availableQuantity;
    private Map<String, String> attributes = new HashMap<>();
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private List<ImageDTO> files;
}
