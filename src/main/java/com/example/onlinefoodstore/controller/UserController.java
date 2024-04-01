package com.example.onlinefoodstore.controller;

import com.example.onlinefoodstore.model.dto.CustomPage;
import com.example.onlinefoodstore.model.dto.Header;
import com.example.onlinefoodstore.model.dto.PaginationRequest;
import com.example.onlinefoodstore.model.dto.UserDTO;
import com.example.onlinefoodstore.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

import static com.example.onlinefoodstore.commons.constans.RestConstants.BASE_URL;

@RestController
@RequestMapping(value = BASE_URL + "/user")
@RequiredArgsConstructor
@SecurityRequirement(name = "online-food-store-bearer-auth")
public class UserController {
    private final UserService userService;

    @GetMapping(path = "/{id}")
    @Cacheable(value = "user", key = "#id")
    public Header<?> getById(@PathVariable UUID id) {
        return Header.ok(userService.getById(id));
    }

    @GetMapping(path = "/all")
    public Header<?> getAllByFilter(PaginationRequest paginationRequest) {
        CustomPage<UserDTO> customPage = userService.getListPagination(paginationRequest);
        return Header.ok(customPage.getData(), customPage.getPaginationData());
    }

    @PostMapping(path = "/add/new-user")
    public Header<?> addNewUser(@RequestBody @Valid UserDTO userDTO) throws MessagingException, IOException {
        userService.addNewUser(userDTO);
        return Header.ok();
    }

    @PutMapping(path = "/{id}")
    @CachePut(cacheNames = "user", key = "#id")
    public Header<?> update(@PathVariable UUID id, @RequestBody UserDTO userDTO) {
        return Header.ok(userService.update(id, userDTO));
    }

    @DeleteMapping
    public Header<?> delete(@RequestParam UUID id) {
        userService.delete(id);
        return Header.ok();
    }
}
