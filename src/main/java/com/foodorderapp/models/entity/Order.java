package com.foodorderapp.models.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

   // @OneToMany(mappedBy="order")
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
//    private List<Product> products;

    private Product product;
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
            Boolean isActive,
            Product product
            //      List<Product> products
    ) {
    //    this.products = products;
        this.userData = userData;
        this.date = date;
        this.sum = sum;
        this.address = address;
        this.isActive = isActive;
        this.product = product;
    }

    @ManyToOne
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }


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
