package com.rtarcisio.inventaryms.dtos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtarcisio.inventaryms.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Builder
@Data
@AllArgsConstructor
public class SkuSimpleDTO {
    private String skuId;
    private String productId;
    private int availableQuantity;
//    private Map<String, String> attributes;
    private String name;
    private String description;
    private BigDecimal price;
    private CategoryEnum category;

    public SkuSimpleDTO() {
    }
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public SkuSimpleDTO(String skuId, String productId, int availableQuantity, /*Map<String, String> attributes*/  String attributesJson, String name, String description, BigDecimal price, CategoryEnum category) {
        this.skuId = skuId;
        this.productId = productId;
        this.availableQuantity = availableQuantity;
//        this.attributes = attributes;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;

        // 🔄 Converte JSON de volta para Map<String, String>
//        try {
//            this.attributes = objectMapper.readValue(attributesJson, Map.class);
//        } catch (Exception e) {
//            this.attributes = new HashMap<>(); // Se der erro, mantém um mapa vazio
//        }
    }
}
