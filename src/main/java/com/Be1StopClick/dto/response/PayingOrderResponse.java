package com.Be1StopClick.dto.response;

/*
 * Created by dendy-prtha on 17/05/2019.
 *
 */

public class PayingOrderResponse {

    private String status;

    private String message;

    private String orderId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
