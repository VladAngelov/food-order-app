package com.foodorderapp.services.interfaces;

import com.foodorderapp.models.service.ProductServiceModel;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductServiceModel findByName(String name);
    List<ProductServiceModel> findByType(String name);
    List<ProductServiceModel> findAll();
    Optional<ProductServiceModel> findById(String id);
    ProductServiceModel addProduct(ProductServiceModel productServiceModel);
    ProductServiceModel editProduct(ProductServiceModel productServiceModel);
    void deleteById(String id);
}
