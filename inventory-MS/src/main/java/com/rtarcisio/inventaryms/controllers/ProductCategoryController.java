package com.rtarcisio.inventaryms.controllers;

import com.rtarcisio.inventaryms.dtos.CategoryDTO;
import com.rtarcisio.inventaryms.enums.CategoryEnum;
import com.rtarcisio.inventaryms.utils.ProductAttributeValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product-category")
public class ProductCategoryController {

    @GetMapping("/{category}")
    public ResponseEntity<Set<String>> getRequiredAttributes(@PathVariable CategoryEnum category) {
        Set<String> requiredFields = ProductAttributeValidator.REQUIRED_ATTRIBUTES.get(category);

        if (requiredFields == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(requiredFields);
    }

    @GetMapping
    public List<CategoryDTO> getCategories(@RequestParam(required = false, defaultValue = "false") boolean onlyMain) {
        return Arrays.stream(CategoryEnum.values())
                .filter(category -> !onlyMain || category.getParentCategory() == null)
                .map(CategoryDTO::fromEnum)
                .collect(Collectors.toList());
    }
}