package com.rtarcisio.inventaryms.utils;

import com.rtarcisio.inventaryms.dtos.input.ProductInputSimple;
import com.rtarcisio.inventaryms.enums.CategoryEnum;
import com.rtarcisio.inventaryms.services.exceptions.templ.FieldMessage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductValidationUtils {

    public static Set<FieldMessage>    validateProductInputSimple(ProductInputSimple input) {
        Set<FieldMessage> list = new HashSet<>();


            if (!CategoryEnum.isValidCategory(input.getCategory())) {
                list.add(new FieldMessage("category", "Invalid Category"));
            }

            System.out.println(input.getCategory());


        return list;
    }
}