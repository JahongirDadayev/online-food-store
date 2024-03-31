package com.example.onlinefoodstore.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public abstract class BaseDTO<ID> {
    @Hidden
    @JsonProperty(value = "id")
    protected ID id;

    @Hidden
    @JsonProperty(value = "createdAt")
    protected Timestamp createdAt;

    @Hidden
    @JsonProperty(value = "createdBy")
    protected UUID createdBy;
}
