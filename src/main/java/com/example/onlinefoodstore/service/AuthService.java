package com.example.onlinefoodstore.service;

import com.example.onlinefoodstore.commons.exception.GeneralApiException;
import com.example.onlinefoodstore.model.dto.AuthDTO;
import com.example.onlinefoodstore.model.dto.TokenModel;
import com.example.onlinefoodstore.model.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public TokenModel signIn(AuthDTO authDTO) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword()));
            User user = (User) authenticate.getPrincipal();
            log.info("User enter to the system {}", user.getUsername());
            return new TokenModel(jwtService.generateToken(user, Map.of("authorities", user.getAuthorities())));
        } catch (Exception e) {
            log.error("Exception occurred in getAnonymousToken method  {}", e.getMessage());
            e.printStackTrace();
            throw new GeneralApiException("Login or password incorrect!");
        }
    }
}
