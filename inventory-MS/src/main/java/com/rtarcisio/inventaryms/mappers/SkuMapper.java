package com.rtarcisio.inventaryms.mappers;

import com.rtarcisio.inventaryms.domains.ImageProduct;
import com.rtarcisio.inventaryms.domains.ProductGroup;
import com.rtarcisio.inventaryms.domains.SkuProduct;
import com.rtarcisio.inventaryms.dtos.ImageDTO;
import com.rtarcisio.inventaryms.dtos.SkuDetailedDTO;
import com.rtarcisio.inventaryms.dtos.SkuSimpleDTO;
import com.rtarcisio.inventaryms.dtos.input.SkuInput;
import com.rtarcisio.inventaryms.enums.CategoryEnum;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class SkuMapper {

    public static SkuProduct inputToModel(SkuInput inputSimple){

        SkuProduct skuProduct = new SkuProduct();
        skuProduct.setPrice(inputSimple.getPrice());
        skuProduct.setTotalQuantity(inputSimple.getTotalQuantity());
        skuProduct.setMinimumThreshold(inputSimple.getMinimumThreshold());
//        skuProduct.setAttributes(inputSimple.getAttributes());
        skuProduct.setIsAvailable(true);
        skuProduct.setCreatedDate(LocalDate.now());
        return skuProduct;
    }

    public static SkuDetailedDTO toDetailedDTO(SkuProduct skuProduct, List<ImageProduct> imageProductsList) throws IOException {
        List<ImageDTO> imageDTOList = imageProductsList.isEmpty() ?
                List.of(ImageMapper.createDefaultImage()) :
                ImageMapper.mapToImageDTOList(ImageMapper.mapToMultipartFileList(imageProductsList));

        SkuDetailedDTO detailedDTO = new SkuDetailedDTO();
        detailedDTO.setAttributes(skuProduct.getAttributes());
//        detailedDTO.setName();
        return null;  //buildSkuDetailedDTO(productGroup, imageDTOList);
    }

    public static SkuSimpleDTO modelToSimpleDto(SkuProduct skuProduct){
//        private String skuId;
//        private String productId;
//        private int availableQuantity;
//        private Map<String, String> attributes;
//        private String name;
//        private String description;
//        private BigDecimal price;
//        private CategoryEnum category;

        return SkuSimpleDTO.builder()
                .skuId(skuProduct.getId())
                .productId(skuProduct.getProductGroup().getId())
                .availableQuantity(skuProduct.getAvailableQuantity())
                .name(skuProduct.getProductGroup().getName())
                .description(skuProduct.getProductGroup().getDescription())
                .price(skuProduct.getPrice())
                .category(skuProduct.getProductGroup().getCategory())
                .build();

//        return null;
    }



    private static SkuDetailedDTO buildSkuDetailedDTO(ProductGroup productGroup, List<ImageDTO> imageDTOList) {
        return SkuDetailedDTO.builder()
//                .id(productGroup.getId())
//                .name(productGroup.getName())
//                .category(productGroup.getCategory().name())
//                .files(imageDTOList)
//				.description(productGroup.getDescription())
//				.price(productGroup.getPrice())
//				.availableQuantity(productGroup.getInventory().getAvailableQuantity())
                .build();
    }
}
