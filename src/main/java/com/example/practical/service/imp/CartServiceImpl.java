package com.example.practical.service.imp;

import com.example.practical.model.CartItem;
import com.example.practical.model.Product;
import com.example.practical.model.User;
import com.example.practical.storage.InMemoryDatabase;
import com.example.practical.service.CartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CartServiceImpl implements CartService {
    private final InMemoryDatabase db;
    private final AtomicLong cartItemIdGenerator = new AtomicLong(1);

    public CartServiceImpl(InMemoryDatabase db) {
        this.db = db;
    }

    @Override
    public void addToCart(Long productId, int quantity, String username) {
        User user = db.users.get(username);
        Product product = db.products.get(productId);

        if (user == null) throw new RuntimeException("User not found");
        if (product == null) throw new RuntimeException("Product not found");
        if (product.getStock() < quantity) throw new RuntimeException("Not enough stock");

        CartItem newItem = CartItem.builder()
                .id(cartItemIdGenerator.getAndIncrement())
                .productId(productId)
                .productName(product.getName())
                .quantity(quantity)
                .price(product.getPrice())
                .build();

        user.getCart().add(newItem);
    }

    @Override
    public void removeFromCart(Long itemId, String username) {
        User user = db.users.get(username);
        if (user != null) {
            user.getCart().removeIf(item -> item.getId().equals(itemId));
        }
    }

    @Override
    public void clearCart(String username) {
        User user = db.users.get(username);
        if (user != null) {
            user.getCart().clear();
        }
    }

    @Override
    public List<CartItem> getCart(String username) {
        User user = db.users.get(username);
        if (user == null) throw new RuntimeException("User not found");
        return new ArrayList<>(user.getCart());
    }

    @Override
    public double calculateTotal(String username) {
        User user = db.users.get(username);
        if (user == null) throw new RuntimeException("User not found");
        return user.getCart().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
}