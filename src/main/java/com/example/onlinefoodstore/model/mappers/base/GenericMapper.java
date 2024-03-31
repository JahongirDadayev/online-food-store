package com.example.onlinefoodstore.model.mappers.base;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface GenericMapper<E,M> {
    E toEntity(M dto);

    M toDto(E entity);

    List<E> toEntityList(List<M> models);


    List<M> toDtoList(List<E> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(@MappingTarget E entity, M model);
}

