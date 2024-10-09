package com.rtarcisio.inventaryms.state;

import com.rtarcisio.inventaryms.domains.Product;

import java.time.LocalDateTime;

/**
 *PENDING, IN_PROGRESS, COMPLETED
 * @author ruantarcisio
 */
public enum ProductState implements ProductStateInterface{
    OUT_OF_STOCK{
        @Override
        public void checkAvailability(Product product) {
            if (product.getInventory().getAvailableQuantity() > product.getInventory().getMinimumThreshold()) {
                product.setState(IN_STOCK);
                product.getInventory().setLastUpdated(LocalDateTime.now());
            }
            else if (product.getInventory().getAvailableQuantity() <= product.getInventory().getMinimumThreshold()) {
                product.setState(LOW_STOCK);
                product.getInventory().setLastUpdated(LocalDateTime.now());
            }
        }

        @Override
        public void notifyUsers(Product product) {

        }
    },
    IN_STOCK{
        @Override
        public void checkAvailability(Product product) {
            if (product.getInventory().getAvailableQuantity() <= product.getInventory().getMinimumThreshold() && product.getInventory().getAvailableQuantity() > 0) {
                product.setState(LOW_STOCK);
                product.getInventory().setLastUpdated(LocalDateTime.now());
            }
            else if (product.getInventory().getAvailableQuantity() == 0) {
                product.setState(OUT_OF_STOCK);
                product.getInventory().setLastUpdated(LocalDateTime.now());
            }
        }

        @Override
        public void notifyUsers(Product product) {

        }
    },
    LOW_STOCK{
        @Override
        public void checkAvailability(Product product) {
            if (product.getInventory().getAvailableQuantity() > product.getInventory().getMinimumThreshold()) {
                product.setState(IN_STOCK);
                product.getInventory().setLastUpdated(LocalDateTime.now());
            }
            else if (product.getInventory().getAvailableQuantity() == 0) {
                product.setState(OUT_OF_STOCK);
                product.getInventory().setLastUpdated(LocalDateTime.now());
            }
        }

        @Override
        public void notifyUsers(Product product) {

        }
    }
}
