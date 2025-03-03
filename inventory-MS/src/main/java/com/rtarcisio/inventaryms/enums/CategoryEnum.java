package com.rtarcisio.inventaryms.enums;

public enum CategoryEnum {

    // 📱 Eletrônicos
    ELECTRONICS("Eletrônicos", null),
    SMARTPHONES("Smartphones", ELECTRONICS),
    LAPTOPS("Laptops", ELECTRONICS),
    TELEVISIONS("Televisores", ELECTRONICS),

    // 🏠 Eletrodomésticos
    APPLIANCES("Eletrodomésticos", null),
    REFRIGERATORS("Geladeiras", APPLIANCES),
    OVENS("Forno & Microondas", APPLIANCES),
    WASHING_MACHINES("Máquinas de Lavar", APPLIANCES),

    // 👕 Moda
    FASHION("Moda", null),
    CLOTHING("Roupas", FASHION),
    SHOES("Calçados", FASHION),
    ACCESSORIES("Acessórios", FASHION),

    // 🚴‍ Esportes
    SPORTS("Esportes", null),
    BICYCLES("Bicicletas", SPORTS),
    GYM_EQUIPMENT("Equipamentos de Academia", SPORTS),
    SPORTS_CLOTHING("Roupas Esportivas", SPORTS),

    // 💄 Beleza
    BEAUTY("Beleza", null),
    MAKEUP("Maquiagem", BEAUTY),
    SKINCARE("Cuidados com a Pele", BEAUTY),
    HAIRCARE("Cuidados com o Cabelo", BEAUTY),

    // 🚗 Automotivo
    AUTOMOTIVE("Automotivo", null),
    CAR_PARTS("Peças de Carro", AUTOMOTIVE),
    MOTORCYCLE_PARTS("Peças de Moto", AUTOMOTIVE),
    ACCESSORIES_AUTOMOTIVE("Acessórios Automotivos", AUTOMOTIVE),

    // 📚 Livros
    BOOKS("Livros", null),
    FICTION("Ficção", BOOKS),
    NON_FICTION("Não Ficção", BOOKS),
    EDUCATIONAL("Educacional", BOOKS),

    // 🛋 Móveis
    FURNITURE("Móveis", null),
    SOFAS("Sofás", FURNITURE),
    TABLES("Mesas", FURNITURE),
    CHAIRS("Cadeiras", FURNITURE),

    // 🛒 Outros
    OTHERS("Outros", null);

    private final String displayName;
    private final CategoryEnum parentCategory;

    CategoryEnum(String displayName, CategoryEnum parentCategory) {
        this.displayName = displayName;
        this.parentCategory = parentCategory;
    }

    public String getDisplayName() {
        return displayName;
    }

    public CategoryEnum getParentCategory() {
        return parentCategory;
    }

    public boolean isSubcategory() {
        return parentCategory != null;
    }
}
