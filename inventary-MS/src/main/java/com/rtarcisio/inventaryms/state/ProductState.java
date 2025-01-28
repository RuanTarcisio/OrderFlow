package com.rtarcisio.inventaryms.state;

import com.rtarcisio.inventaryms.domains.Inventory;
import com.rtarcisio.inventaryms.domains.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author ruantarcisio
 */
public enum ProductState implements ProductStateInterface{
    OUT_OF_STOCK{
        @Override
        public void checkAvailability(Product product) {

            Inventory inventory = product.getInventory();
            if (inventory.getAvailableQuantity() > inventory.getMinimumThreshold()) {
                product.setState(BACK_IN_STOCK);
                product.notifyUsers();
            }
            else if (inventory.getAvailableQuantity() < inventory.getMinimumThreshold()) {
                product.setState(LOW_STOCK);
                product.notifyUsers();
            }
            inventory.setLastUpdated(LocalDateTime.now());
            product.setLastCheck(LocalDate.now());
        }

        @Override
        public void notifyUsers(Product product) {
        }
    },
    IN_STOCK{
        @Override
        public void checkAvailability(Product product) {
            Inventory inventory = product.getInventory();
            if (inventory.getAvailableQuantity() <= inventory.getMinimumThreshold() && inventory.getAvailableQuantity() > 0) {
                product.setState(LOW_STOCK);
                notifyUsers(product);
            }
            else if (inventory.getAvailableQuantity() == 0) {
                product.setState(OUT_OF_STOCK);
            }
            product.setLastCheck(LocalDate.now());

        }

        @Override
        public void notifyUsers(Product product) {

        }
    },
    LOW_STOCK{
        @Override
        public void checkAvailability(Product product) {
            Inventory inventory = product.getInventory();
            if (inventory.getAvailableQuantity() > inventory.getMinimumThreshold()) {
                product.setState(IN_STOCK);
            }
            else if (inventory.getAvailableQuantity() == 0) {
                product.setState(OUT_OF_STOCK);
                notifyUsers(product);
            }
            product.setLastCheck(LocalDate.now());
        }

        @Override
        public void notifyUsers(Product product) {

        }
    }
    ,NEW_PRODUCT{
        @Override
        public void checkAvailability(Product product) {

            product.setLastCheck(LocalDate.now());

        }

        @Override
        public void notifyUsers(Product product) {

        }
    },
    BACK_IN_STOCK{
        @Override
        public void checkAvailability(Product product) {

            product.setLastCheck(LocalDate.now());

        }

        @Override
        public void notifyUsers(Product product) {

        }
    }
}
