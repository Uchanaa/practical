package com.example.practical.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private Long id;
    private String name;
    private double price;
    private int stock;
}