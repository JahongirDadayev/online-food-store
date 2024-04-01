package com.example.onlinefoodstore.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {
    @Email(message = "Email isn't correct!")
    @JsonProperty(value = "username")
    private String username;

    @JsonProperty(value = "password")
    private String password;
}
