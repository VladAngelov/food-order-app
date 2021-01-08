package com.foodorderapp.models.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

//    @OneToMany(mappedBy="order")
   // private List<Product> products;
    private String userData;
    private String date;
    private BigDecimal sum;
    private String address;
    private Boolean isActive;

    public Order() {
    }

    public Order(
            String userData,
            String date,
            BigDecimal sum,
            String address,
            Boolean isActive
    ) {
     //   this.products = new ArrayList<Product>();
        this.userData = userData;
        this.date = date;
        this.sum = sum;
        this.address = address;
        this.isActive = isActive;
    }

//    @Column(name = "products", nullable = false)
//    public List<Product> getProducts() {
//        return products;
//    }
//    public void setProduct(List<Product> products) {
//        this.products = products;
//    }

    @Column(name = "userData", nullable = false)
    public String getUserData() {
        return userData;
    }
    public void setUserData(String userData) {
        this.userData = userData;
    }

    @Column(name = "date", nullable = false)
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    @Column(name = "sum", nullable = false)
    public BigDecimal getSum() {
        return sum;
    }
    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Column(name = "address", nullable = false)
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "isActive", nullable = false)
    public Boolean getActive() {
        return isActive;
    }
    public void setActive(Boolean active) {
        isActive = active;
    }

}
