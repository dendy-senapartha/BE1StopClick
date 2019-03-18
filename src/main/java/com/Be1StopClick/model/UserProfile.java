package com.Be1StopClick.model;

/*
 * Created by dendy-prtha on 05/03/2019.
 * model for user_profile table of database
 */

import javax.persistence.*;

@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "dob")
    private String dob;
    @Column(name = "phone")
    private String phone;
    @Column(name = "image_url")
    private String imageUrl;

    public UserProfile() {
    }

    public UserProfile(Long id, String name, String dob, String phone
            , String profile_photo) {
        this.setId(id);
        this.setName(name);
        this.setDob(dob);
        this.setPhone(phone);
        this.setImageUrl(profile_photo);
    }

    @Override
    public String toString() {
        return "UserProfile [id=" + getId() + ", name=" + name +
                ", dob=" + dob + ", phone=" + phone + ", imageUrl=" + imageUrl + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}