package com.example.practical.service.imp;


import com.example.practical.model.Product;
import com.example.practical.storage.InMemoryDatabase;
import com.example.practical.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final InMemoryDatabase db;

    public ProductServiceImpl(InMemoryDatabase db) {
        this.db = db;
    }

    @Override
    public Product createProduct(Product product) {
        Long id = db.productIdGenerator.getAndIncrement();
        product.setId(id);
        db.products.put(id, product);
        return product;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        if (!db.products.containsKey(id)) {
            throw new RuntimeException("Product not found");
        }
        product.setId(id);
        db.products.put(id, product);
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        db.products.remove(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(db.products.values());
    }

    @Override
    public Product getProductById(Long id) {
        return db.products.get(id);
    }
}