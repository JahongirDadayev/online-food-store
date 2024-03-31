package com.example.onlinefoodstore.service;

import com.example.onlinefoodstore.commons.exception.GeneralApiException;
import com.example.onlinefoodstore.commons.utils.GenerationPassword;
import com.example.onlinefoodstore.model.dto.EmailDTO;
import com.example.onlinefoodstore.model.dto.UserDTO;
import com.example.onlinefoodstore.model.entities.User;
import com.example.onlinefoodstore.model.enums.EAuthority;
import com.example.onlinefoodstore.model.mappers.UserMapper;
import com.example.onlinefoodstore.repository.UserRepository;
import com.example.onlinefoodstore.service.base.BaseService;
import jakarta.mail.MessagingException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService extends BaseService<User, UserDTO, UserMapper, UUID, UserRepository> {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    public UserService(UserRepository repository,UserMapper mapper, PasswordEncoder passwordEncoder, EmailService emailService) {
        super(repository, mapper, User.class);
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public void addNewUser(UserDTO userDTO) throws MessagingException, IOException {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new GeneralApiException("User already exists!");
        }
        String password = GenerationPassword.generationPassword();
        User user = User.builder().
                firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(password))
                .enabled(true)
                .authorities(Set.of(EAuthority.USER))
                .build();
        userRepository.save(user);
        emailService.postEmail(EmailDTO.builder().subject("Invitation to join John Company").email(userDTO.getUsername()).password(password).build());
    }
}
