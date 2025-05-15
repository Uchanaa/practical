package com.example.practical.model;


import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class Order {
    private Long id;
    private String userId;  // ეს უნდა იყოს String, რომ ემთხვეოდეს User-ის username-ს
    private List<CartItem> items;
    private double totalPrice;
    private String status;
}