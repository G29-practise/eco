package org.example.eco.common.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.eco.common.mapper.GenericMapper;
import org.example.eco.common.repository.GenericRepository;
import org.example.eco.common.rsql.SpecificationBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public abstract class GenericService<ENTITY, ID, CREATE_DTO, RESPONSE_DTO, UPDATE_DTO> {
    protected abstract GenericRepository<ENTITY, ID> getRepository();

    protected abstract Class<ENTITY> getEntityClass();

    protected abstract GenericMapper<ENTITY, CREATE_DTO, RESPONSE_DTO, UPDATE_DTO> getMapper();

    protected abstract RESPONSE_DTO internalCreate(CREATE_DTO createDto);

    public RESPONSE_DTO create(CREATE_DTO createDto) {
        return internalCreate(createDto);
    }

    protected abstract RESPONSE_DTO internalUpdate(ID id, UPDATE_DTO updateDto);

    public RESPONSE_DTO update(ID id, UPDATE_DTO updateDto) {
        return internalUpdate(id, updateDto);
    }

    @Transactional
    public Page<RESPONSE_DTO> getAll(String predicate, Pageable pageable) {
        Specification<ENTITY> specification = SpecificationBuilder.build(predicate);
        Page<ENTITY> page;
        if (specification == null) {
            page = getRepository().findAll(pageable);
        } else {
            page = getRepository().findAll(specification, pageable);
        }

        return page.map(entity -> getMapper().toResponseDto(entity));
    }

    @Transactional
    public RESPONSE_DTO get(ID id) {
        ENTITY entity = getRepository().findById(id).orElseThrow(
                () -> new EntityNotFoundException("%s not found"
                        .formatted(getEntityClass()
                                .getSimpleName())));

        return getMapper().toResponseDto(entity);
    }

    @Transactional
    public void delete(ID id) {
        ENTITY entity = getRepository()
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("%s not found"
                        .formatted(getEntityClass().getSimpleName())));
        getRepository().deleteById(id);
    }
}
