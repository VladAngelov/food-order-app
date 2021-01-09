package com.foodorderapp.models.binding.product;

import com.foodorderapp.constants.Errors;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProductAddBindingModel {

    private String name;
    private String content;
    private String type;
    private Integer volume;
    private double price;
//    private byte[] picBytes;

    public ProductAddBindingModel(
            String name,
            String content,
            String type,
            Integer volume,
            double price
//            byte[] picBytes
    ) {
        this.name = name;
        this.content = content;
        this.type = type;
        this.volume = volume;
        this.price = price;
//        this.picBytes = picBytes;
    }

    @Length(min = 3, message = Errors.NAME_ERROR)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 3, message = Errors.DESCRIPTION_ERROR)
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Length(min = 4, message = Errors.PRODUCT_TYPE_ERROR)
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Min(value = 10, message = Errors.VOLUME_ERROR)
    public Integer getVolume() {
        return volume;
    }
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    @DecimalMin(value = "0.01", message = Errors.PRICE_ERROR)
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

//    @NotNull(message = Errors.IMAGE_ERROR)
//    public byte[] getPicBytes() {
//        return picBytes;
//    }
//    public void setPicBytes(byte[] picBytes) {
//        this.picBytes = picBytes;
//    }
}
