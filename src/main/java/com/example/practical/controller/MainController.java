package com.example.practical.controller;

import com.example.practical.model.Product;
import com.example.practical.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class MainController {

    private final ProductService productService;

    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public String saveProduct(@RequestBody Product product) {
        productService.handleProduct(product);
        return "Product handled successfully!";
    }
}
