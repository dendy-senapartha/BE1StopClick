package com.Be1StopClick.dto;

import com.Be1StopClick.model.Invoice;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/*
 * Created by dendy-prtha on 01/07/2019.
 * order  DTO
 */

public class OrderDTO {
    private int id;

    private String orderTitle;

    private Date orderDate;

    private BigDecimal totalAmount;

    private InvoiceDTO invoice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }


    public InvoiceDTO getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceDTO invoice) {
        this.invoice = invoice;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }
}
