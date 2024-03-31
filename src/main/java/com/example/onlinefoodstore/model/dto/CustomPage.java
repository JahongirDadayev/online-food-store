package com.example.onlinefoodstore.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomPage<T> {

    private List<T> data;
    @JsonProperty(value = "paginationData")
    private PaginationData paginationData;

    public static <T> CustomPage<T> of(Page<T> page) {
        List<T> tList = page.getContent();
        return CustomPage.<T>builder()
                .paginationData(buildPaginationData(page))
                .data(tList)
                .build();
    }

    private static <T> PaginationData buildPaginationData(Page<T> page) {
        return PaginationData.builder()
                .numberOfElements(page.getNumberOfElements())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .pageSize(page.getSize())
                .currentPageNumber(page.getNumber())
                .build();
    }

    public static <T, E> CustomPage<T> of(Page<E> page, List<T> dtoList) {
        return CustomPage.<T>builder()
                .paginationData(buildPaginationData(page))
                .data(dtoList)
                .build();
    }
}
