package com.foodorderapp.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "images")
public class Image extends  BaseEntity{
    private String name;
    private String type;
    private byte[] picByte;

    public Image() {
       super();
    }
    public Image(
            String name,
            String type,
            byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "picByte", length = 100000, nullable = false)
    public byte[] getPicByte() {
        return picByte;
    }
    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }
}

