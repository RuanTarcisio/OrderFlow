package com.rtarcisio.orderms.state;

import com.rtarcisio.orderms.domains.Order;

public enum  OrderState implements OrderStateInterface {
    NEW{
        @Override
        public void next(Order order) {
            order.setState(OrderState.PENDING_PAYMENT);
        }

        @Override
        public void previous(Order order) {
        }

        @Override
        public void cancel(Order order) {
            order.setState(OrderState.CANCELLED);
        }
    },
    PENDING_PAYMENT{
        @Override
        public void next(Order order) {
            order.setState(OrderState.PAID);
        }

        @Override
        public void previous(Order order) {
            order.setState(OrderState.NEW);
        }

        @Override
        public void cancel(Order order) {
            order.setState(OrderState.CANCELLED);
        }
    },
    PAID{

        @Override
        public void next(Order order) {
            order.setState(OrderState.PROCESSING);
        }

        @Override
        public void previous(Order order) {
            order.setState(OrderState.PENDING_PAYMENT);
        }

        @Override
        public void cancel(Order order) {
            order.setState(OrderState.CANCELLED);
        }
    },
    PROCESSING{
        @Override
        public void next(Order order) {
            order.setState(OrderState.SHIPPED);
        }

        @Override
        public void previous(Order order) {
            order.setState(OrderState.PAID);
        }

        @Override
        public void cancel(Order order) {
            order.setState(OrderState.CANCELLED);
        }
    },
    SHIPPED{
        @Override
        public void next(Order order) {
            order.setState(OrderState.DELIVERED);
        }

        @Override
        public void previous(Order order) {
            throw new UnsupportedOperationException("Cannot cancel order after it's been shipped.");
        }

        @Override
        public void cancel(Order order) {
            throw new UnsupportedOperationException("Cannot cancel order after it's been shipped.");
        }
    },
    DELIVERED{
        @Override
        public void next(Order order) {
            order.setState(OrderState.COMPLETED);
        }

        @Override
        public void previous(Order order) {
            throw new UnsupportedOperationException("Cannot cancel order after delivery.");
        }

        @Override
        public void cancel(Order order) {
            throw new UnsupportedOperationException("Cannot cancel order after delivery.");
        }
    },
    CANCELLED{
        @Override
        public void next(Order order) {
            throw new UnsupportedOperationException("Cannot proceed with a cancelled order.");
        }

        @Override
        public void previous(Order order) {
            throw new UnsupportedOperationException("Cannot revert a cancelled order.");
        }

        @Override
        public void cancel(Order order) {
            throw new UnsupportedOperationException("Order is already cancelled.");
        }
    },
    RETURNED{
        @Override
        public void next(Order order) {
            throw new UnsupportedOperationException("Cannot move to the next state from RETURNED.");
        }

        @Override
        public void previous(Order order) {
            throw new UnsupportedOperationException("Cannot move to the next state from RETURNED.");
        }

        @Override
        public void cancel(Order order) {
            throw new UnsupportedOperationException("Cannot move to the next state from RETURNED.");
        }
    },
    COMPLETED{
        @Override
        public void next(Order order) {
            // Não há transição após a conclusão
            throw new UnsupportedOperationException("Order is already completed.");
        }

        @Override
        public void previous(Order order) {
            order.setState(OrderState.DELIVERED);
        }

        @Override
        public void cancel(Order order) {
            throw new UnsupportedOperationException("Cannot cancel a completed order.");
        }
    };
}