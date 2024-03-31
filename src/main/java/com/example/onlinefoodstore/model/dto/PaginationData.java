package com.example.onlinefoodstore.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationData {
    @JsonProperty(value = "numberOfElements")
    private int numberOfElements;

    @JsonProperty(value = "totalElements")
    private long totalElements;

    @JsonProperty(value = "totalPages")
    private int totalPages;

    @JsonProperty(value = "currentPageNumber")
    private int currentPageNumber;

    @JsonProperty(value = "pageSize")
    private int pageSize;
}
