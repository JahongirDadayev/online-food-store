package com.example.onlinefoodstore.service;

import com.example.onlinefoodstore.model.dto.ProductDTO;
import com.example.onlinefoodstore.model.entities.Product;
import com.example.onlinefoodstore.model.mappers.ProductMapper;
import com.example.onlinefoodstore.repository.ProductRepository;
import com.example.onlinefoodstore.service.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService<Product, ProductDTO, ProductMapper, Long, ProductRepository> {
    public ProductService(ProductRepository repository, ProductMapper mapper) {
        super(repository, mapper, Product.class);
    }
}
