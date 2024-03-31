package com.example.onlinefoodstore.service;

import com.example.onlinefoodstore.model.dto.CategoryDTO;
import com.example.onlinefoodstore.model.entities.Category;
import com.example.onlinefoodstore.model.mappers.CategoryMapper;
import com.example.onlinefoodstore.repository.CategoryRepository;
import com.example.onlinefoodstore.repository.ProductRepository;
import com.example.onlinefoodstore.service.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseService<Category, CategoryDTO, CategoryMapper, Long, CategoryRepository> {
    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository repository, CategoryMapper mapper, ProductRepository productRepository) {
        super(repository, mapper, Category.class);
        this.productRepository = productRepository;
    }

    @Override
    public CategoryDTO getById(Long aLong) {
        CategoryDTO categoryDTO = super.getById(aLong);
        categoryDTO.setCountOfProduct(productRepository.countByCategoryId(aLong));
        return categoryDTO;
    }
}
