package com.rtarcisio.inventaryms.state;

import com.rtarcisio.inventaryms.domains.ProductGroup;

public interface ProductStateInterface {
    void checkAvailability(ProductGroup productGroup);
    void notifyUsers(ProductGroup productGroup);
}
