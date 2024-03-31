package com.example.onlinefoodstore.controller;

import com.example.onlinefoodstore.model.dto.*;
import com.example.onlinefoodstore.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;

import static com.example.onlinefoodstore.commons.constans.RestConstants.BASE_URL;

@RestController
@RequestMapping(value = BASE_URL + "/product")
@RequiredArgsConstructor
@SecurityRequirement(name = "online-food-store-bearer-auth")
public class ProductController {
    private final ProductService productService;


    @GetMapping(path = "/{id}")
    public Header<?> getById(@PathVariable Long id) {
        return Header.ok(productService.getById(id));
    }

    @GetMapping(path = "/all")
    public Header<?> getAllByFilter(PaginationRequest paginationRequest) {
        CustomPage<ProductDTO> customPage = productService.getListPagination(paginationRequest);
        return Header.ok(customPage.getData(), customPage.getPaginationData());
    }

    @PostMapping
    public Header<?> addNewUser(@RequestBody ProductDTO productDTO) {
        return Header.ok(productService.create(productDTO));
    }

    @PutMapping(path = "/{id}")
    public Header<?> update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return Header.ok(productService.update(id, productDTO));
    }

    @DeleteMapping
    public Header<?> delete(@RequestParam Long id) {
        productService.delete(id);
        return Header.ok();
    }
}
