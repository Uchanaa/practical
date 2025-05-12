package com.example.practical.config;

import com.example.practical.service.FoodProductService;
import com.example.practical.service.ProductService;
import com.example.practical.service.TechProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public ProductService productService() {
        return new TechProductService(); // ან → new FoodProductService();
    }
}
