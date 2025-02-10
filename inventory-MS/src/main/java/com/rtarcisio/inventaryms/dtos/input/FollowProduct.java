package com.rtarcisio.inventaryms.dtos.input;

import jakarta.validation.constraints.NotNull;

public record FollowProduct(@NotNull Long idProduct, @NotNull String userEmail) {
}
