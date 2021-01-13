package com.foodorderapp.services.impl;

import com.foodorderapp.models.entity.Order;
import com.foodorderapp.models.entity.Product;
import com.foodorderapp.models.service.OrderServiceModel;
import com.foodorderapp.models.service.ProductServiceModel;
import com.foodorderapp.repositories.OrderRepository;
import com.foodorderapp.services.interfaces.OrderService;
import com.foodorderapp.services.interfaces.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final ProductService productService;

    @Autowired
    public OrderServiceImpl(
            OrderRepository orderRepository,
            ModelMapper modelMapper,
            ProductService productService
    ) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.productService = productService;
    }

    @Override
    public List<OrderServiceModel> findAll() {
        List<Order> ordersDb = this.orderRepository.findAll();
        List<OrderServiceModel> ordersSM = new ArrayList<OrderServiceModel>();
        List<ProductServiceModel> products = new ArrayList<>();

        for (Order order : ordersDb) {
            for(var id : order.getProductsIds().split(" ")) {
                products.add(this.productService.findById(id));
            }
            OrderServiceModel orderSM = new OrderServiceModel();
            orderSM.setProducts(products);
            orderSM.setActive(order.getActive());
            orderSM.setDate(order.getDate());
            orderSM.setId(order.getId());
            orderSM.setSum(order.getSum());
            orderSM.setUserData(order.getUserData());
            orderSM.setAddress(order.getAddress());

            ordersSM.add(orderSM);
        }

        return ordersSM;
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
            StringBuilder productsIds = new StringBuilder();

            for (ProductServiceModel product : orderServiceModel.getProducts())
            {
                Product productModel = this.modelMapper.map(product, Product.class);
                products.add(productModel);
                newOrder.getProducts().add(productModel);
                productsIds.append(product.getId());
                productsIds.append(" ");
            }

            newOrder.setProductsIds(productsIds.toString().trim());
            newOrder.setProducts(products);

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
