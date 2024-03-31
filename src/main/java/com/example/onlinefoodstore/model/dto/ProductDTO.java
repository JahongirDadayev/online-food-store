package com.example.onlinefoodstore.model.dto;

import com.example.onlinefoodstore.model.enums.MeasureTypes;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO extends BaseDTO<Long> {
    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "quantity")
    private Double quantity;

    @JsonProperty(value = "measureType")
    private MeasureTypes measureType;

    @JsonProperty(value = "expireDate")
    private LocalDate expireDate;

    @JsonProperty(value = "categoryId")
    private Long categoryId;
}
