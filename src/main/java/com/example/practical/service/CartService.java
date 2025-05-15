package com.example.practical.service;

import com.example.practical.model.CartItem;
import java.util.List;

public interface CartService {
    void addToCart(Long productId, int quantity, String username); // ცვლილება: long → Long
    void removeFromCart(Long itemId, String username);
    void clearCart(String username);
    List<CartItem> getCart(String username);
    double calculateTotal(String username);
}