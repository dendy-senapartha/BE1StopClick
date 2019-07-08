package com.Be1StopClick.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * Created by dendy-prtha on 31/05/2019.
 * Orders entity
 */

@Entity
@Table(name = "orders")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "order",
            orphanRemoval = true
    )
    @JsonIgnoreProperties("order")
    private List<OrderItem> orderItemList = new ArrayList<>();

    @OneToOne(mappedBy = "orders",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("orders")
    private Invoice invoice;

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

    public Invoice getInvoice() {
        return invoice;
    }

    public void addInvoice(Invoice invoice) {
        this.invoice = invoice;
        invoice.setOrders(this);
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItemList.add(orderItem);
        orderItem.setOrder(this);
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItemList.remove(orderItem);
        orderItem.setOrder(null);
    }

    public void clearOrderItem() {
        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrder(null);
        }

        orderItemList.clear();
    }
}
