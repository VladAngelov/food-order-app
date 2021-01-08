package com.foodorderapp.models.view;

public class ProductViewModel {
    private String id;
    private String name;
    private String productDescription;
    private String productType;
    private Integer volume;
    private double price;
    private byte[] picture;

    public ProductViewModel(
            String id,
            String name,
            String productDescription,
            String productType,
            Integer volume,
            double price,
            byte[] picture
    ) {
        this.id = id;
        this.name = name;
        this.productDescription = productDescription;
        this.productType = productType;
        this.volume = volume;
        this.price = price;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getProductDescription() {
        return productDescription;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductType() {
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getVolume() {
        return volume;
    }
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public byte[] getPicture() {
        return picture;
    }
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
