package com.example.onlinefoodstore.service.base;

import com.example.onlinefoodstore.commons.exception.GeneralApiException;
import com.example.onlinefoodstore.model.annotations.SearchableField;
import com.example.onlinefoodstore.model.dto.BaseDTO;
import com.example.onlinefoodstore.model.dto.CustomPage;
import com.example.onlinefoodstore.model.dto.PaginationRequest;
import com.example.onlinefoodstore.model.mappers.base.GenericMapper;
import com.example.onlinefoodstore.repository.base.BaseRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseService<E, DTO extends BaseDTO<ID>, M extends GenericMapper<E, DTO>, ID, R extends BaseRepository<E, ID>> {
    private final R repository;

    private final M mapper;

    private final Class<E> entityTypeClass;

    public DTO getById(ID id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new GeneralApiException(entityTypeClass.getSimpleName() + " not found!")));
    }

    public CustomPage<DTO> getListPagination(PaginationRequest paginationRequest) {
        String keyword = paginationRequest.getSearch();
        if (Objects.nonNull(keyword) && !(keyword.isEmpty() || keyword.isBlank())) {
            Specification<E> spec = getSearchable(keyword);
            Page<E> page = getPage(spec, paginationRequest);
            return CustomPage.of(page, mapper.toDtoList(page.getContent()));
        } else {
            Page<E> page = getPage(paginationRequest);
            return CustomPage.of(page, mapper.toDtoList(page.getContent()));
        }
    }

    public DTO create(DTO dto) {
        E entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public DTO update(ID id, DTO dto) {
        E entity = repository.findById(id).orElseThrow(() -> new GeneralApiException(entityTypeClass.getSimpleName() + " not found!"));
        mapper.updateEntityFromDto(entity, dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public void delete(ID id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception occurred in the Remove request field {0}", e);
            throw new GeneralApiException("An error occurred!");
        }
    }

    protected Page<E> getPage(PaginationRequest request) {
        return repository.findAll(request.getPageRequest());
    }

    protected Page<E> getPage(Specification<E> specification, PaginationRequest request) {
        Pageable pageable = request.getPageRequest();
        return repository.findAll(specification, pageable);
    }

    protected Specification<E> getSearchable(String keyword) {
        if (Objects.isNull(keyword)) return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        Field[] fields = entityTypeClass.getDeclaredFields();
        return (root, query, builder) -> {
            List<Predicate> joinPredicates = new ArrayList<>();
            Arrays.stream(fields).forEach(
                    field -> {
                        SearchableField fieldAnnotation = field.getAnnotation(SearchableField.class);
                        if (Objects.nonNull(fieldAnnotation) && fieldAnnotation.value()) {
                            boolean isTable = false;
                            for (Annotation annotation : field.getType().getAnnotations()) {
                                if (annotation.annotationType().equals(Entity.class)) {
                                    isTable = true;
                                    break;
                                }
                            }
                            if (isTable) {
                                if (!fieldAnnotation.innerSearField().isEmpty() || !fieldAnnotation.innerSearField().isBlank()) {
                                    Join<Object, Object> join = root.join(field.getName(), JoinType.LEFT);
                                    joinPredicates.add(builder.like(builder.lower(builder.toString(join.get(fieldAnnotation.innerSearField()))), "%" + keyword.toLowerCase() + "%"));
                                }
                            } else {
                                joinPredicates.add(builder.like(builder.lower(builder.toString(root.get(field.getName()))), "%" + keyword.toLowerCase() + "%"));
                            }
                        }
                    }
            );
            return joinPredicates.isEmpty() ? builder.disjunction() : builder.or(joinPredicates.toArray(new Predicate[]{}));
        };
    }
}
