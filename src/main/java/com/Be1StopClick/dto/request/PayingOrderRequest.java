package com.Be1StopClick.dto.request;

import java.util.List;

/*
 * Created by dendy-prtha on 17/05/2019.
 *
 */

public class PayingOrderRequest {

    private String userId;

    private String orderId;

    private String paymentMethodId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }
}
