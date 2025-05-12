package com.example.practical.model;

public class Product {
    private String name;
    private double price;

    // კონსტრუქტორი, გეთერები, სეთერები
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
