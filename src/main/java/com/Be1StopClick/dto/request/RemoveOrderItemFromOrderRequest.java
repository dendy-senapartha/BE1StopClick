package com.Be1StopClick.dto.request;

/*
 * Created by dendy-prtha on 17/05/2019.
 *
 */

public class RemoveOrderItemFromOrderRequest {

    private String orderId;

    private OrderItem orderItem;

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public static class OrderItem {
        private String productId;
        private String quantity;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

    }

}
