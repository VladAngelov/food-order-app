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
    private String content;
    private String type;
    private Integer volume;
    private BigDecimal price;

    public ProductServiceModel() {
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
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
