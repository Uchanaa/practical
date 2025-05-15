package com.example.practical.model;

import lombok.Builder;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class User {
    private String username;
    private double budget;
    private List<CartItem> cart;
    private boolean isAdmin;
}