package com.example.onlinefoodstore.model.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationRequest {
    private int page;
    
    private int size = 20;

    private String search;

    public int getPage() {
        return Math.max(page, 0);
    }

    @Hidden
    public Pageable getPageRequest() {
        return PageRequest.of(getPage(), getSize());
    }
}
