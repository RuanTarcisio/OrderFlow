package com.rtarcisio.inventaryms.dtos.input;

import com.rtarcisio.inventaryms.validations.FileContentType;
import com.rtarcisio.inventaryms.validations.FileSize;
import com.rtarcisio.inventaryms.validations.ProductDetailedInsert;
import com.rtarcisio.inventaryms.validations.ProductInsert;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@ProductDetailedInsert(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE}, max = "1MB")
public class ProductInputDetailed extends ProductInputSimple {
    @NotNull private Integer availableQuantity;
    @NotNull private Integer minimumThreshold;
    @NotNull private Integer reorderLevel;
    private List <MultipartFile> files;


}
