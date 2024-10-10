package com.rtarcisio.inventaryms.dtos.input;

import com.rtarcisio.inventaryms.validations.ProductInsert;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

@ProductInsert
public class ProductInputDetailed extends ProductInputSimple{
    @NotEmpty private Integer availableQuantity;
    @NotEmpty private Integer minimumThreshold;
    @NotEmpty private Integer reorderLevel;


}
