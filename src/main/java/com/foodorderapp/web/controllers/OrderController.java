package com.foodorderapp.web.controllers;

import com.foodorderapp.constants.Links;
import com.foodorderapp.models.binding.order.OrderAddBindingModel;
import com.foodorderapp.models.binding.order.OrderEditBindingModel;
import com.foodorderapp.models.service.OrderServiceModel;
import com.foodorderapp.models.view.OrderViewModel;
import com.foodorderapp.services.interfaces.OrderService;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Links.API)
public class OrderController {
    private final OrderService orderService;
    private  final ModelMapper modelMapper;

    public OrderController(
            OrderService orderService,
            ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(path = Links.ORDER_ALL)
    public ResponseEntity<?> getAll() {
        try {
            List<OrderViewModel> orders = new ArrayList<>();
            List<OrderServiceModel> ordersDB = this.orderService.findAll();

            for (var order : ordersDB) {
                OrderViewModel orderViewModel = this.modelMapper
                                .map(order, OrderViewModel.class);
                orders.add(orderViewModel);
            }

            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = Links.ORDER_ADD)
    public ResponseEntity<?> addOrder(
            @RequestBody OrderAddBindingModel orderAddBindingModel ) {
        try {
            orderAddBindingModel.setActive(true);
            OrderServiceModel orderServiceModel = this.modelMapper
                    .map(orderAddBindingModel, OrderServiceModel.class);

            OrderServiceModel order = this.orderService.addOrder(orderServiceModel);

            OrderViewModel orderViewModel = this.modelMapper
                    .map(order, OrderViewModel.class);

            return new ResponseEntity<>(orderViewModel, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = Links.ORDER_BY_ID)
    public  ResponseEntity<?> getOrder(@PathVariable("id") String id) {
        try {
            Optional<OrderServiceModel> orderServiceModel = this.orderService.findById(id);
             OrderViewModel orderViewModel = this.modelMapper.map(orderServiceModel.get(), OrderViewModel.class);

            return new ResponseEntity<>(orderViewModel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping(path = Links.ORDER_EDIT)
    public ResponseEntity<?> editOrder(
            @RequestBody OrderEditBindingModel orderEditBindingModel) {
        try {
            OrderEditBindingModel orderExist = this.modelMapper
                    .map(this.orderService
                            .findById(orderEditBindingModel.getId()),
                    OrderEditBindingModel.class);

            if (orderExist != null) {
                orderExist.setActive(orderEditBindingModel.getActive());

                OrderServiceModel orderServiceModel = this.modelMapper
                        .map(orderExist, OrderServiceModel.class);

                return new ResponseEntity<>(this.modelMapper.map(
                                this.orderService.addOrder(orderServiceModel),
                                OrderViewModel.class),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
