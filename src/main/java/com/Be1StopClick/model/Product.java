package com.Be1StopClick.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * Created by dendy-prtha on 05/04/2019.
 * Product model
 */

@Entity
@Table(name = "product")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "package_code")
    private BigDecimal packageCode;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    @Type(type = "text")
    private String description;

    @Column(name = "compatibility")
    private String compatibility;

    @Column(name = "status")
    private String status;

    @Column(name = "created", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @OneToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;

    @OneToMany(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("product")
    private List<ProductImage> productImageList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("product")
    private List<Track> trackList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "order"
    )
    @JsonIgnoreProperties("product")
    private List<OrderItem> orderItemList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(BigDecimal packageCode) {
        this.packageCode = packageCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
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

    public Category getCategory() {
        return (Category) Hibernate.unproxy(category);
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Subcategory getSubcategory() {
        return (Subcategory) Hibernate.unproxy(subcategory);
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public List<ProductImage> getProductImageList() {
        return productImageList;
    }

    public void setProductImageList(List<ProductImage> productImageList) {
        this.productImageList = productImageList;
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItemList.add(orderItem);
        orderItem.setProduct(this);
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItemList.remove(orderItem);
        orderItem.setProduct(null);
    }
}
