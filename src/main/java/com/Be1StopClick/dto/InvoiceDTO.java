package com.Be1StopClick.dto;

import com.Be1StopClick.model.Invoice;

import java.math.BigDecimal;
import java.util.Date;
/*
 * Created by dendy-prtha on 01/07/2019.
 * order  DTO
 */

public class InvoiceDTO {

    private int id;

    private PaymentMethodDTO paymentMethod;

    private String description;

    private String status;

    private Date created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PaymentMethodDTO getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodDTO paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
