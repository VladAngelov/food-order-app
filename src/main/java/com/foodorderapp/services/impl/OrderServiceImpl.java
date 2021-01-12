package com.foodorderapp.services.impl;

import com.foodorderapp.models.entity.Order;
import com.foodorderapp.models.entity.Product;
import com.foodorderapp.models.service.OrderServiceModel;
import com.foodorderapp.models.service.ProductServiceModel;
import com.foodorderapp.repositories.OrderRepository;
import com.foodorderapp.services.interfaces.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

        var ordersDb = this.orderRepository.findAll();
        var orderSM = new ArrayList<OrderServiceModel>();
        for (Order order : ordersDb) {
            orderSM.add(this.modelMapper.map(order, OrderServiceModel.class));
        }

        return orderSM;
//        return this.orderRepository
//                .findAll()
//                .stream()
//                .map(order -> this.modelMapper
//                        .map(order, OrderServiceModel.class))
//                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderServiceModel> findById(String id) {
        var order = this.orderRepository.findById(id);
        return Optional.of(this.modelMapper
                .map(order, OrderServiceModel.class));
    }

    @Override
    public OrderServiceModel addOrder(OrderServiceModel orderServiceModel) {
        try {

            Order newOrder = new Order();

            newOrder.setActive(orderServiceModel.getActive());
            newOrder.setAddress(orderServiceModel.getAddress());
            newOrder.setSum(orderServiceModel.getSum());
            newOrder.setDate(orderServiceModel.getDate());
            newOrder.setUserData(orderServiceModel.getUserData());

            List<Product> products = new ArrayList<>();
            for (ProductServiceModel product : orderServiceModel.getProducts())
            {
                Product productModel = this.modelMapper.map(product, Product.class);
                products.add(productModel);
            }
            newOrder.setProduct(products);

            this.orderRepository.save(newOrder);
            orderServiceModel.setId(newOrder.getId());
            return orderServiceModel;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public OrderServiceModel editOrder(OrderServiceModel orderServiceModel) {
        try {
            Optional<Order> orderDb = this.orderRepository.findById(orderServiceModel.getId());
            Order order = this.modelMapper.map(orderDb, Order.class);
            this.orderRepository.save(order);
            return this.modelMapper.map(order, OrderServiceModel.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<OrderServiceModel> findByIsActive(Boolean isActive) {
        var allOrders =
                this.orderRepository
                        .findAll().stream().filter(Order::getActive)
                        .collect(Collectors.toList());

        var activeOrders = new ArrayList<OrderServiceModel>();

        for (Order order : allOrders) {
            activeOrders.add(this.modelMapper.map(order, OrderServiceModel.class));
        }

        return activeOrders;
    }
}
