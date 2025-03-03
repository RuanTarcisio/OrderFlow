package com.rtarcisio.inventaryms.dtos.input;

import com.rtarcisio.inventaryms.validations.ProductInsert;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ProductInsert
public class ProductInputSimple {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String category;

}
