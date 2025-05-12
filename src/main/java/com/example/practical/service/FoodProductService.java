package com.example.practical.service;

import com.example.practical.model.Product;
import org.springframework.stereotype.Service;

@Service
public class FoodProductService implements ProductService {

    @Override
    public void handleProduct(Product product) {
        System.out.println("Saving food product: " + product.getName() + ", Price: " + product.getPrice());
    }
}
