package com.foodorderapp.models.view;

import com.foodorderapp.models.service.ProductServiceModel;
import java.math.BigDecimal;
import java.util.List;

public class OrderViewModel {
    private List<ProductServiceModel> products;
    private String userData;
    private String date;
    private BigDecimal sum;
    private String address;
    private  Boolean isActive;

    public OrderViewModel(
            List<ProductServiceModel> products,
            String userData,
            String date,
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

    public List<ProductServiceModel> getProducts() {
        return products;
    }
    public void setProducts(List<ProductServiceModel> products) {
        this.products = products;
    }

    public String getUserData() {
        return userData;
    }
    public void setUserData(String userData) {
        this.userData = userData;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
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
