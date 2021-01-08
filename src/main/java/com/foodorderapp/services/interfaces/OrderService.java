package com.foodorderapp.services.interfaces;

import com.foodorderapp.models.service.OrderServiceModel;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderServiceModel> findAll();
    Optional<OrderServiceModel> findById(String id);
    OrderServiceModel addOrder(OrderServiceModel orderServiceModel);
    OrderServiceModel editOrder(OrderServiceModel orderServiceModel);
    List<OrderServiceModel> findByIsActive(Boolean isActive);
}
