package com.rtarcisio.inventaryms.dtos.projection;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductProjection {
    Long getId();
    String getName();
    BigDecimal getPrice();
    Long getCategory();
    Long getMainImageId();
}