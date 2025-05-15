package com.example.practical.controller;

import com.example.practical.model.CartItem;
import com.example.practical.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public void addToCart(@RequestParam Long productId,
                          @RequestParam int quantity,
                          @RequestHeader String username) {
        cartService.addToCart(productId, quantity, username);
    }

    @DeleteMapping("/remove/{itemId}")
    public void removeFromCart(@PathVariable Long itemId,
                               @RequestHeader String username) {
        cartService.removeFromCart(itemId, username);
    }

    @DeleteMapping("/clear")
    public void clearCart(@RequestHeader String username) {
        cartService.clearCart(username);
    }

    @GetMapping
    public List<CartItem> getCart(@RequestHeader String username) {
        return cartService.getCart(username);
    }

    @GetMapping("/total")
    public double calculateTotal(@RequestHeader String username) {
        return cartService.calculateTotal(username);
    }
}