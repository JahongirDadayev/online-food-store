package com.example.onlinefoodstore.repository;

import com.example.onlinefoodstore.model.entities.Product;
import com.example.onlinefoodstore.repository.base.BaseRepository;

public interface ProductRepository extends BaseRepository<Product, Long> {
    Long countByCategoryId(Long categoryId);
}
