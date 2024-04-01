package com.example.onlinefoodstore.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseDTO<UUID> implements Serializable {
    @JsonProperty(value = "firstName")
    private String firstName;

    @JsonProperty(value = "lastName")
    private String lastName;

    @Email(message = "Email isn't correct!")
    @JsonProperty(value = "username")
    private String username;
}
