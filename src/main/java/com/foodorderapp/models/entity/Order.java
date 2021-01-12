package com.foodorderapp.models.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    private List<Product> product;
    private String userData;
    private String date;
    private BigDecimal sum;
    private String address;
    private Boolean isActive;

    public Order() {
    }

    @ManyToMany(targetEntity = Product.class)
    @JoinTable(name = "orders_products",
            joinColumns = { @JoinColumn(name = "order_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id", referencedColumnName = "id") }
    )

    public List<Product> getProduct() {
        return product;
    }
    public void setProduct(List<Product> product) {
        this.product = product;
    }

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
