package com.example.practical.storage;


import com.example.practical.model.Product;
import com.example.practical.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryDatabase {
    public final Map<Long, Product> products = new HashMap<>();
    public final Map<String, User> users = new HashMap<>();
    public final AtomicLong productIdGenerator = new AtomicLong(1);
    public final AtomicLong orderIdGenerator = new AtomicLong(1);

    public InMemoryDatabase() {
        // Initialize admin and user
        users.put("admin", User.builder()
                .username("admin")
                .budget(Double.MAX_VALUE)
                .cart(new ArrayList<>())
                .isAdmin(true)
                .build());

        users.put("user", User.builder()
                .username("user")
                .budget(1000)
                .cart(new ArrayList<>())
                .isAdmin(false)
                .build());
    }
}