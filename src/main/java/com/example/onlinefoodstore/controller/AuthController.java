package com.example.onlinefoodstore.controller;

import com.example.onlinefoodstore.model.dto.AuthDTO;
import com.example.onlinefoodstore.model.dto.Header;
import com.example.onlinefoodstore.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/sign-in")
    public Header<?> signIn(@RequestBody AuthDTO authDTO){
        return Header.ok(authService.signIn(authDTO));
    }
}
