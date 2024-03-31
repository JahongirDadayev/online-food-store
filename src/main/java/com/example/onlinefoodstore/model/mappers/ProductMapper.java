package com.example.onlinefoodstore.model.mappers;

import com.example.onlinefoodstore.model.dto.ProductDTO;
import com.example.onlinefoodstore.model.entities.Product;
import com.example.onlinefoodstore.model.mappers.base.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper extends GenericMapper<Product, ProductDTO> {
}
