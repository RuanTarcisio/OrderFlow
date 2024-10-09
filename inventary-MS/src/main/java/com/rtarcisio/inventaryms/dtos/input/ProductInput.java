package com.rtarcisio.inventaryms.dtos.input;

import com.rtarcisio.inventaryms.validations.ProductInsert;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

@ProductInsert
public record ProductInput(@NotEmpty String name, @NotEmpty String description, @NotEmpty BigDecimal price, @NotEmpty String category,
                           @NotEmpty Integer availableQuantity, @NotEmpty Integer minimumThreshold, @NotEmpty Integer reorderLevel) {


}
