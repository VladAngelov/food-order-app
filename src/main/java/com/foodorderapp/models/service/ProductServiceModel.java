package com.foodorderapp.models.service;

import com.foodorderapp.constants.Errors;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductServiceModel {

    private String id;
    private String name;
    private String productDescription;
    private String productType;
    private Integer volume;
    private BigDecimal price;
    private byte[] picBytes;

    public ProductServiceModel(
            String id,
            String name,
            String productDescription,
            String productType,
            Integer volume,
            BigDecimal price,
            byte[] picBytes
    ) {
        this.id = id;
        this.name = name;
        this.productDescription = productDescription;
        this.productType = productType;
        this.volume = volume;
        this.price = price;
        this.picBytes = picBytes;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Length(min = 3, message = Errors.NAME_ERROR)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 3, message = Errors.DESCRIPTION_ERROR)
    public String getProductDescription() {
        return productDescription;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Length(min = 4, message = Errors.PRODUCT_TYPE_ERROR)
    public String getProductType() {
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Min(value = 10, message = Errors.VOLUME_ERROR)
    public Integer getVolume() {
        return volume;
    }
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    @DecimalMin(value = "0.01", message = Errors.PRICE_ERROR)
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotNull(message = Errors.IMAGE_ERROR)
    public byte[] getPicBytes() {
        return picBytes;
    }
    public void setPicBytes(byte[] picBytes) {
        this.picBytes = picBytes;
    }
}
