package com.rtarcisio.inventaryms.dtos.input;

import jakarta.validation.constraints.NotNull;

public record InventoryInputUpdate (@NotNull int newStock) {
}
