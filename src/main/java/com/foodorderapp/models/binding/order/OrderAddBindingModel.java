package com.foodorderapp.models.binding.order;

import com.foodorderapp.constants.Errors;
import com.foodorderapp.models.service.ProductServiceModel;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class OrderAddBindingModel {
    private List<ProductServiceModel> products;
    private String userData;
    private String date;
    private BigDecimal sum;
    private String address;
    private Boolean isActive;

    public OrderAddBindingModel( ) { }

    @NotNull
    public List<ProductServiceModel> getProducts() {
        return products;
    }
    public void setProducts(List<ProductServiceModel> products) {
        this.products = products;
    }

    @NotNull
    public String getUserData() {
        return userData;
    }
    public void setUserData(String userData) {
        this.userData = userData;
    }

    @NotNull
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    @DecimalMin(value = "5.00", message = Errors.ORDER_SUM_ERROR)
    public BigDecimal getSum() {
        return sum;
    }
    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @NotNull
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @NotNull
    public Boolean getActive() {
        return isActive;
    }
    public void setActive(Boolean active) {
        isActive = active;
    }
}
