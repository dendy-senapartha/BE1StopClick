package com.Be1StopClick.dto.response;

import com.Be1StopClick.dto.InvoiceDTO;
import com.Be1StopClick.dto.OrderItemDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/*
 * Created by dendy-prtha on 01/07/2019.
 * order  DTO
 */

public class GetOrderDetailsResponse {
    private int id;

    private Date orderDate;

    private BigDecimal totalAmount;

    private List<OrderItemDTO> orderItemList;

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

    public List<OrderItemDTO> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemDTO> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
