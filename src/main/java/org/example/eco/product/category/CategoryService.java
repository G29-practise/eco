package org.example.eco.product.category;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.eco.common.service.GenericService;
import org.example.eco.product.category.dto.CategoryCreateDto;
import org.example.eco.product.category.dto.CategoryResponseDto;
import org.example.eco.product.category.dto.CategoryUpdateDto;
import org.example.eco.product.category.entity.Category;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class CategoryService extends GenericService<Category, UUID, CategoryCreateDto, CategoryResponseDto, CategoryUpdateDto> {

    private final CategoryDtoMapper mapper;
    private final CategoryRepository repository;
    private final Class<Category>entityClass = Category.class;

    @Override
    protected CategoryResponseDto internalCreate(CategoryCreateDto categoryCreateDto) {
        Category entity = mapper.toEntity(categoryCreateDto);
        entity.setId(UUID.randomUUID());
        Category saved = repository.save(entity);
        return mapper.toResponseDto(saved);
    }

    @Override
    protected CategoryResponseDto internalUpdate(UUID uuid, CategoryUpdateDto categoryUpdateDto) {
        Category category = repository.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException("Category with id: %s not fount".formatted(uuid)));
        mapper.toEntity(categoryUpdateDto,category);
        Category saved = repository.save(category);

        return mapper.toResponseDto(saved);
    }
    public CategoryResponseDto getByName(String name){
        Category category = repository
                .findByName(name)
                .orElseThrow(
                        () -> new EntityNotFoundException("Category with name: %s not found".formatted(name)));
        return mapper.toResponseDto(category);
    }
}
