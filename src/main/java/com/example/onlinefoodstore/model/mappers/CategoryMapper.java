package com.example.onlinefoodstore.model.mappers;

import com.example.onlinefoodstore.model.dto.CategoryDTO;
import com.example.onlinefoodstore.model.entities.Category;
import com.example.onlinefoodstore.model.mappers.base.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends GenericMapper<Category, CategoryDTO> {
}
