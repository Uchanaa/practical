package com.example.practical.service;

import com.example.practical.model.Order;
import java.util.List;

public interface OrderService {
    Order checkout(String username);
    List<Order> getUserOrders(String username);
    List<Order> getAllOrders();
    Order getOrderById(Long id);
}