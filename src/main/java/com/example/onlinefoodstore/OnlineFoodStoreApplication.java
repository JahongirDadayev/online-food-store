package com.example.onlinefoodstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OnlineFoodStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineFoodStoreApplication.class, args);
    }
}
