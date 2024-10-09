package com.rtarcisio.inventaryms.dtos.input;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public record ProductInputSimple(@NotEmpty String name, @NotEmpty String description, @NotEmpty BigDecimal price, @NotEmpty String category) {


}
