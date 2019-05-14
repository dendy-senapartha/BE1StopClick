package com.Be1StopClick.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

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
    @Type(type="text")
    private String description;

    @Column(name = "compatibility")
    private String compatibility;

    @Column(name = "urldownload")
    private String urldownload;

    @Column(name = "status")
    private String status;

    @Column(name = "created", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;

    @Column(name = "product_art")
    private String productArt;

    @Column(name = "product_backdrop")
    private String productBackdrop;

    @Column(name = "youtube_trailer_id")
    private String youtubeTrailerId;

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

    public String getUrldownload() {
        return urldownload;
    }

    public void setUrldownload(String urldownload) {
        this.urldownload = urldownload;
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
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public String getProductArt() {
        return productArt;
    }

    public void setProductArt(String productArt) {
        this.productArt = productArt;
    }

    public String getProductBackdrop() {
        return productBackdrop;
    }

    public void setProductBackdrop(String productBackdrop) {
        this.productBackdrop = productBackdrop;
    }

    public String getYoutubeTrailerId() {
        return youtubeTrailerId;
    }

    public void setYoutubeTrailerId(String youtubeTrailerId) {
        this.youtubeTrailerId = youtubeTrailerId;
    }
}
