package com.rtarcisio.inventaryms.state;

import com.rtarcisio.inventaryms.domains.Product;

public interface ProductStateInterface {
    void checkAvailability(Product product);
    void notifyUsers(Product product);
}
