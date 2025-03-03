package com.rtarcisio.inventaryms.dtos;

import com.rtarcisio.inventaryms.enums.CategoryEnum;

public record CategoryDTO(String name, String displayName, String parentCategory) {
    public static CategoryDTO fromEnum(CategoryEnum category) {
        return new CategoryDTO(
                category.name(),
                category.getDisplayName(),
                category.getParentCategory() != null ? category.getParentCategory().name() : null
        );
    }
}