package com.foodorderapp.models.service;

import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class ImageServiceModel {
    private String name;
    private String type;
    private byte[] picByte;

    public ImageServiceModel() { }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @NotNull
    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }
}
