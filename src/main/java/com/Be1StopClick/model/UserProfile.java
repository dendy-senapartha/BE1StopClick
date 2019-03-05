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
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "dob")
    private String dob;
    @Column(name = "phone")
    private String phone;
    @Column(name = "profile_photo")
    private String profilePhoto;

    public UserProfile() {
    }

    public UserProfile(Long id, String firstName, String lastName, String dob, String phone
            , String profile_photo) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setDob(dob);
        this.setPhone(phone);
        this.setProfilePhoto(profile_photo);
    }

    @Override
    public String toString() {
        return "UserProfile [id=" + getId() + ", firstName=" + firstName + ", lastName=" + lastName +
                ", dob=" + dob + ", phone=" + phone + ", profilePhoto=" + profilePhoto + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}