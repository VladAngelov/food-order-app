package com.foodorderapp.services.impl;

import com.foodorderapp.models.entity.Order;
import com.foodorderapp.models.service.OrderServiceModel;
import com.foodorderapp.repositories.OrderRepository;
import com.foodorderapp.services.interfaces.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(
            OrderRepository orderRepository,
            ModelMapper modelMapper
    ) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OrderServiceModel> findAll() {
        return this.orderRepository
                .findAll()
                .stream()
                .map(order -> this.modelMapper
                        .map(order, OrderServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderServiceModel> findById(String id) {
        return Optional.of(this.modelMapper
                .map(this.orderRepository.findById(id), OrderServiceModel.class));
    }

    @Override
    public OrderServiceModel addOrder(OrderServiceModel orderServiceModel) {
        try {
            this.orderRepository.saveAndFlush(
                    this.modelMapper.map(orderServiceModel, Order.class));

            return orderServiceModel;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public OrderServiceModel editOrder(OrderServiceModel orderServiceModel) {
        try {
            this.orderRepository.save(
                    this.modelMapper.map(orderServiceModel, Order.class));

            return orderServiceModel;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<OrderServiceModel> findByIsActive(Boolean isActive) {
        var orders =  this.orderRepository.findAll();

        return orders.stream().filter(o -> o.getActive())
                     .map(order -> this.modelMapper
                        .map(order, OrderServiceModel.class))
                     .collect(Collectors.toList());
    }
}
