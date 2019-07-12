package com.Be1StopClick.dto.request;

import java.util.List;

/*
 * Created by dendy-prtha on 17/05/2019.
 *
 */

public class AddOrderItemToOrderRequest {

    private String userId;

    private OrderItem orderItem;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
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
