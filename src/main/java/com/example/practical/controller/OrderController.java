package com.example.practical.controller;

import com.example.practical.model.Order;
import com.example.practical.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/checkout")
    public Order checkout(@RequestHeader String username) {
        return orderService.checkout(username);
    }

    @GetMapping
    public List<Order> getUserOrders(@RequestHeader String username) {
        return orderService.getUserOrders(username);
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }
}