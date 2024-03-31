package com.example.onlinefoodstore.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDTO {
    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "subject")
    private String subject;

    @JsonProperty(value = "password")
    private String password;
}