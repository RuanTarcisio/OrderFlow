package com.rtarcisio.inventaryms.utils;

import com.rtarcisio.inventaryms.enums.CategoryEnum;

import java.util.*;

public class ProductAttributeValidator {
    public static final Map<CategoryEnum, Set<String>> REQUIRED_ATTRIBUTES = new HashMap<>();

    static {
        // 🔌 Eletrônicos (Categoria Principal)
        Set<String> electronicsAttributes = Set.of("Marca", "Modelo", "Voltagem", "Garantia");
        REQUIRED_ATTRIBUTES.put(CategoryEnum.ELECTRONICS, electronicsAttributes);

        REQUIRED_ATTRIBUTES.put(CategoryEnum.SMARTPHONES, mergeAttributes(CategoryEnum.ELECTRONICS, Set.of(
                "Sistema Operacional", "Tamanho da Tela", "Memória RAM", "Armazenamento",
                "Número de Câmeras", "Capacidade da Bateria", "Tipo de Conexão (4G/5G)"
        )));

        REQUIRED_ATTRIBUTES.put(CategoryEnum.LAPTOPS, mergeAttributes(CategoryEnum.ELECTRONICS, Set.of(
                "Processador", "Memória RAM", "Armazenamento", "Tamanho da Tela", "Placa de Vídeo"
        )));

        REQUIRED_ATTRIBUTES.put(CategoryEnum.TELEVISIONS, mergeAttributes(CategoryEnum.ELECTRONICS, Set.of(
                "Resolução", "Tamanho da Tela", "Tipo de Tela", "Smart TV"
        )));

        // 👕 Moda (Categoria Principal)
        Set<String> fashionAttributes = Set.of("Marca", "Tamanho", "Cor", "Material", "Gênero");
        REQUIRED_ATTRIBUTES.put(CategoryEnum.FASHION, fashionAttributes);

        REQUIRED_ATTRIBUTES.put(CategoryEnum.SHOES, mergeAttributes(CategoryEnum.FASHION, Set.of(
                "Tipo de Solado", "Material Externo", "Fechamento (Cadarço/Velcro)", "Indicação de Uso (Casual, Esportivo, Formal)"
        )));

        REQUIRED_ATTRIBUTES.put(CategoryEnum.CLOTHING, mergeAttributes(CategoryEnum.FASHION, Set.of(
                "Tipo de Tecido", "Estampa"
        )));

        REQUIRED_ATTRIBUTES.put(CategoryEnum.ACCESSORIES, mergeAttributes(CategoryEnum.FASHION, Set.of(
                "Tipo de Acessório", "Gênero"
        )));

        // 📚 Livros (Categoria Principal)
        Set<String> bookAttributes = Set.of("Título", "Autor", "Editora", "Ano de Publicação", "ISBN", "Número de Páginas", "Idioma", "Formato (Físico/Digital)");
        REQUIRED_ATTRIBUTES.put(CategoryEnum.BOOKS, bookAttributes);

        REQUIRED_ATTRIBUTES.put(CategoryEnum.FICTION, mergeAttributes(CategoryEnum.BOOKS, Set.of(
                "Gênero Literário", "Faixa Etária"
        )));

        REQUIRED_ATTRIBUTES.put(CategoryEnum.NON_FICTION, mergeAttributes(CategoryEnum.BOOKS, Set.of(
                "Categoria (Autoajuda, História, Ciência, etc.)"
        )));

        REQUIRED_ATTRIBUTES.put(CategoryEnum.EDUCATIONAL, mergeAttributes(CategoryEnum.BOOKS, Set.of(
                "Nível Educacional (Fundamental, Médio, Superior)", "Área de Estudo"
        )));

        // 🏠 Eletrodomésticos (Categoria Principal)
        Set<String> appliancesAttributes = Set.of("Marca", "Modelo", "Voltagem", "Garantia", "Eficiência Energética");
        REQUIRED_ATTRIBUTES.put(CategoryEnum.APPLIANCES, appliancesAttributes);

        REQUIRED_ATTRIBUTES.put(CategoryEnum.REFRIGERATORS, mergeAttributes(CategoryEnum.APPLIANCES, Set.of(
                "Capacidade (Litros)", "Tipo de Degelo", "Número de Portas", "Painel Digital"
        )));

        REQUIRED_ATTRIBUTES.put(CategoryEnum.OVENS, mergeAttributes(CategoryEnum.APPLIANCES, Set.of(
                "Tipo (Forno/Microondas)", "Capacidade (Litros)", "Potência", "Funções Especiais"
        )));

        REQUIRED_ATTRIBUTES.put(CategoryEnum.WASHING_MACHINES, mergeAttributes(CategoryEnum.APPLIANCES, Set.of(
                "Capacidade (Kg)", "Tipo (Lavadora/Secadora)", "Número de Programas", "Consumo de Água"
        )));
    }

    public static Set<String> getRequiredAttributes(CategoryEnum category) {
        return REQUIRED_ATTRIBUTES.getOrDefault(category, Collections.emptySet());
    }

    private static Set<String> mergeAttributes(CategoryEnum parentCategory, Set<String> additionalAttributes) {
        Set<String> parentAttributes = REQUIRED_ATTRIBUTES.getOrDefault(parentCategory, Collections.emptySet());
        Set<String> mergedAttributes = new HashSet<>(parentAttributes);
        mergedAttributes.addAll(additionalAttributes);
        return mergedAttributes;
    }
}
