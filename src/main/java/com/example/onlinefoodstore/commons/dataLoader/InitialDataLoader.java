package com.example.onlinefoodstore.commons.dataLoader;

import com.example.onlinefoodstore.model.entities.User;
import com.example.onlinefoodstore.model.enums.EAuthority;
import com.example.onlinefoodstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class InitialDataLoader {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Bean
    public void init() {
        userRepository.save(User.builder()
                .username("dadayevjahongir82@gmail.com")
                .firstName("Jahongir")
                .lastName("Dadayev")
                .password(passwordEncoder.encode("Jahongir1234"))
                .authorities(Collections.singleton(EAuthority.USER))
                .enabled(true)
                .build());
    }
}
