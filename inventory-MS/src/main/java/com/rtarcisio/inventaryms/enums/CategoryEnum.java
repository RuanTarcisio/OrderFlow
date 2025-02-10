package com.rtarcisio.inventaryms.enums;

public enum CategoryEnum {
    ELECTRONICS,
    CLOTHING,
    FOOD,
    BOOKS;

    public static boolean isValidCategory(String category) {
        for (CategoryEnum c : CategoryEnum.values()) {
            if (c.name().equalsIgnoreCase(category)) {
                return true;
            }
        }
        return false;
    }
}