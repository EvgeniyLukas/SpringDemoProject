package com.advboard.demo;


import org.springframework.stereotype.Indexed;

import javax.persistence.*;


@Entity
@Indexed
public class Advertisement {
    private Long id;
    private Long user;
    private String title;
    private String description;
    private String contactName;
    private String contactPhone;
    private int price;
    private String picture;


    public Advertisement() {
    }

    public Advertisement(Long id, Long user, String title, String description, String contactName, String contactPhone, int price, String picture) {
        super();
        this.id = id;
        this.user = user;
        this.title = title;
        this.description = description;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.price = price;
        this.picture = picture;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


}
