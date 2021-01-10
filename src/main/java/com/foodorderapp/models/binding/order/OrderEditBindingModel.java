package com.foodorderapp.models.binding.order;

import com.foodorderapp.models.service.ProductServiceModel;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class OrderEditBindingModel {
    private Set<ProductServiceModel> products;
    private String userData;
    private LocalDateTime date;
    private BigDecimal sum;
    private String address;
    private Boolean isActive;

    public OrderEditBindingModel() { }

    public Set<ProductServiceModel> getProducts() {
        return products;
    }
    public void setProducts(Set<ProductServiceModel> products) {
        this.products = products;
    }

    public String getUserData() {
        return userData;
    }
    public void setUserData(String userData) {
        this.userData = userData;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getSum() {
        return sum;
    }
    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getActive() {
        return isActive;
    }
    public void setActive(Boolean active) {
        isActive = active;
    }
}
