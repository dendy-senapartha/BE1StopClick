package com.Be1StopClick.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

/*
 * Created by dendy-prtha on 15/05/2019.
 * Product Image Model
 */

@Entity
@Table(name = "product_image")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "image_type_id")
    private ProductImageType productImageType;

    @Column(name = "image_url")
    private String imageUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductImageType getProductImageType() {
        return (ProductImageType) Hibernate.unproxy(productImageType);
    }

    public void setProductImageType(ProductImageType productImageType) {
        this.productImageType = productImageType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
