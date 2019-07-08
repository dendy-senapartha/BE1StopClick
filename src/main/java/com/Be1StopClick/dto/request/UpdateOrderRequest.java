package com.Be1StopClick.dto.request;

import com.Be1StopClick.dto.OrderItemDTO;

import java.util.List;

/*
 * Created by dendy-prtha on 17/05/2019.
 * Create Order DTO
 * Draft, Issued, Paid, Void(https://www.replicon.com/help/setting-the-status-of-an-invoice/)
 */

public class UpdateOrderRequest {

    private String orderId;

    private List<OrderItem> orderItemList;

    private String paymentMethodId;

    private String invoiceStatus;

    private String description;

    private String OrderDate;

    public class OrderItem {
        private String productId;
        private String quantity;
        private String subtotal;

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

        public String getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(String subtotal) {
            this.subtotal = subtotal;
        }
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
