package com.example.onlinefoodstore.controller;

import com.example.onlinefoodstore.model.dto.CategoryDTO;
import com.example.onlinefoodstore.model.dto.CustomPage;
import com.example.onlinefoodstore.model.dto.Header;
import com.example.onlinefoodstore.model.dto.PaginationRequest;
import com.example.onlinefoodstore.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import static com.example.onlinefoodstore.commons.constans.RestConstants.BASE_URL;

@RestController
@RequestMapping(value = BASE_URL + "/category")
@RequiredArgsConstructor
@SecurityRequirement(name = "online-food-store-bearer-auth")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping(path = "/{id}")
    @Cacheable(value = "category", key = "#id")
    public Header<?> getById(@PathVariable Long id) {
        return Header.ok(categoryService.getById(id));
    }

    @GetMapping(path = "/all")
    public Header<?> getAllByFilter(PaginationRequest paginationRequest) {
        CustomPage<CategoryDTO> customPage = categoryService.getListPagination(paginationRequest);
        return Header.ok(customPage.getData(), customPage.getPaginationData());
    }

    @PostMapping
    public Header<?> addNewUser(@RequestBody CategoryDTO categoryDTO) {
        return Header.ok(categoryService.create(categoryDTO));
    }

    @PutMapping(path = "/{id}")
    @CachePut(cacheNames = "category", key = "#id")
    public Header<?> update(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        return Header.ok(categoryService.update(id, categoryDTO));
    }

    @DeleteMapping
    public Header<?> delete(@RequestParam Long id) {
        categoryService.delete(id);
        return Header.ok();
    }
}
