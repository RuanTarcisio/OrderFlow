package com.rtarcisio.inventaryms.dtos.input;

import com.rtarcisio.inventaryms.validations.FileContentDetailed;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FileContentDetailed(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE}, max = "1MB")
public class SkuInput {

    @NotNull private BigDecimal price;
    @NotNull private Integer totalQuantity;
    @NotNull private Integer minimumThreshold;
    private List <MultipartFile> files;
    @NotNull
    private Map<String, String> attributes = new HashMap<>();

}
