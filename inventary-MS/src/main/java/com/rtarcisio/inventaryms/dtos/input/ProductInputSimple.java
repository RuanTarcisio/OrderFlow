package com.rtarcisio.inventaryms.dtos.input;

import com.rtarcisio.inventaryms.validations.ProductInsert;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor

@ProductInsert
public class ProductInputSimple {
    @NotEmpty private String name;
    @NotEmpty private String description;
    @NotEmpty private BigDecimal price;
    @NotEmpty private String category;

}
