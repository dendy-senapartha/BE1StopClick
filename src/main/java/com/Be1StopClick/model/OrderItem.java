package com.Be1StopClick.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;

/*
 * Created by dendy-prtha on 15/05/2019.
 * order_item Model
 */

@Entity
@Table(name = "order_item")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnoreProperties("orderItemList")
    private Orders order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("orderItemList")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return (Product) Hibernate.unproxy(product);
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders getOrder() {
        return (Orders) Hibernate.unproxy(order);
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof OrderItem)) return false;
        return id == ((OrderItem) object).getId();
    }
}
