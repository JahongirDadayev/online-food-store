package com.example.onlinefoodstore.model.entities;

import com.example.onlinefoodstore.model.annotations.SearchableField;
import com.example.onlinefoodstore.model.entities.base.BaseEntitySequentialID;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "category")
@Builder
public class Category extends BaseEntitySequentialID {
    @SearchableField
    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Product> products;
}
