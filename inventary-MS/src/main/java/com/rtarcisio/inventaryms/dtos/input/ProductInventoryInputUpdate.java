package com.rtarcisio.inventaryms.dtos.input;

import jakarta.validation.constraints.NotNull;

public record ProductInventoryInputUpdate (@NotNull Integer minimumThreshold, @NotNull Integer reorderLevel, @NotNull Integer quantity){
}
