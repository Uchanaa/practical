package com.example.practical.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItem {
    private Long id;
    private Long productId;
    private String productName;
    private int quantity;
    private double price;
}