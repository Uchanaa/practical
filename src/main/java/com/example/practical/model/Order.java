package com.example.practical.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Order.java გამოსწორება:
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private String userId;  // უნდა იყოს String რომ ემთხვეოდეს User-ის username-ს
    private List<CartItem> items;
    private double totalPrice;
    private String status;
}