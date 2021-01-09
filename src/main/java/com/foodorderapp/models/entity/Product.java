package com.foodorderapp.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    private String name;
    private String content;
    private String type;
    private Integer volume;
    private double price;
   // private byte[] picBytes;
    private Order order;

    public Product() {
    }

    public Product(
            String name,
            String content,
            String type,
            Integer volume,
            double price,
            Order order
        //    byte[] picBytes
    ) {
        this.name = name;
        this.content = content;
        this.type = type;
        this.volume = volume;
        this.price = price;
        this.order = order;
      //  this.picBytes = picBytes;
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", nullable = false)
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "type", nullable = false)
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "volume", nullable = false)
    public Integer getVolume() {
        return volume;
    }
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    @Column(name = "price", nullable = false)
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

//    @Column(name = "picture", nullable = false, length = 1000)
//    public byte[] getPicBytes() {
//        return picBytes;
//    }
//    public void setPicBytes(byte[] picBytes) {
//        this.picBytes = picBytes;
//    }

    @ManyToOne
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
}
