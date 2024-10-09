package com.rtarcisio.inventaryms.dtos;

import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public record ProductDTO(@NotEmpty String name, @NotEmpty String description, @NotEmpty BigDecimal price, @NotEmpty String category) {
}
