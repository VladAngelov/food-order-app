package com.foodorderapp.models.view;

public class ProductViewModel {
    private String id;
    private String name;
    private String content;
    private String type;
    private Integer volume;
    private double price;
   // private byte[] picture;

    public ProductViewModel(
            String id,
            String name,
            String content,
            String type,
            Integer volume,
            double price
          //  byte[] picture
    ) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.type = type;
        this.volume = volume;
        this.price = price;
       // this.picture = picture;
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

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
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

//    public byte[] getPicture() {
//        return picture;
//    }
//    public void setPicture(byte[] picture) {
//        this.picture = picture;
//    }
}
