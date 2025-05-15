package com.example.practical.service.imp;

import com.example.practical.model.*;
import com.example.practical.storage.InMemoryDatabase;
import com.example.practical.service.CartService;
import com.example.practical.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final InMemoryDatabase db;
    private final CartService cartService;

    public OrderServiceImpl(InMemoryDatabase db, CartService cartService) {
        this.db = db;
        this.cartService = cartService;
    }

    @Override
    public Order checkout(String username) {
        User user = db.users.get(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        List<CartItem> cart = cartService.getCart(username);
        if (cart.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double total = calculateTotal(cart);
        if (user.getBudget() < total) {
            throw new RuntimeException("Not enough budget");
        }

        // Check and update stock
        for (CartItem item : cart) {
            Product product = db.products.get(item.getProductId());
            if (product == null) {
                throw new RuntimeException("Product not found");
            }
            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }
            product.setStock(product.getStock() - item.getQuantity());
        }

        // Update user budget
        user.setBudget(user.getBudget() - total);

        // Create order
        Order order = Order.builder()
                .id(db.orderIdGenerator.getAndIncrement())
                .userId(user.getUsername())
                .items(new ArrayList<>(cart))
                .totalPrice(total)
                .status("COMPLETED")
                .build();

        db.orders.put(order.getId(), order);
        cartService.clearCart(username);
        return order;
    }

    private double calculateTotal(List<CartItem> cart) {
        return cart.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    @Override
    public List<Order> getUserOrders(String username) {
        return db.orders.values().stream()
                .filter(order -> order.getUserId().equals(username))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getAllOrders() {
        return List.of();
    }

    @Override
    public Order getOrderById(Long id) {
        return null;
    }
}