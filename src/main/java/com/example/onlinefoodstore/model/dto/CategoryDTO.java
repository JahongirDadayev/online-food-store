package com.example.onlinefoodstore.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO extends BaseDTO<Long> implements Serializable {
    @JsonProperty(value = "name")
    private String name;

    @Hidden
    @JsonProperty(value = "countOfProduct")
    private Long countOfProduct;
}
