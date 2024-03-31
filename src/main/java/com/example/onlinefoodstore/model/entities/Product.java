package com.example.onlinefoodstore.model.entities;

import com.example.onlinefoodstore.model.annotations.SearchableField;
import com.example.onlinefoodstore.model.entities.base.BaseEntitySequentialID;
import com.example.onlinefoodstore.model.enums.MeasureTypes;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")
@Builder
public class Product extends BaseEntitySequentialID {
    @SearchableField
    @Column(name = "name")
    private String name;

    @SearchableField
    @Column(name = "quantity")
    private Double quantity;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "measure_type")
    private MeasureTypes measureType;

    @Column(name = "expire_date")
    private LocalDate expireDate;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @SearchableField(innerSearField = "name")
    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;
}
