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

    public OrderEditBindingModel(
            Set<ProductServiceModel> products,
            String userData,
            LocalDateTime date,
            BigDecimal sum,
            String address,
            Boolean isActive) {
        this.products = products;
        this.userData = userData;
        this.date = date;
        this.sum = sum;
        this.address = address;
        this.isActive = isActive;
    }

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
