package org.example.eco.product.category;

import lombok.RequiredArgsConstructor;
import org.example.eco.common.mapper.GenericMapper;
import org.example.eco.product.category.dto.CategoryCreateDto;
import org.example.eco.product.category.dto.CategoryResponseDto;
import org.example.eco.product.category.dto.CategoryUpdateDto;
import org.example.eco.product.category.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryDtoMapper extends GenericMapper<Category, CategoryCreateDto, CategoryResponseDto, CategoryUpdateDto> {

    private final ModelMapper mapper;
    @Override
    public Category toEntity(CategoryCreateDto categoryCreateDto) {
        return mapper.map(categoryCreateDto,Category.class);
    }

    @Override
    public CategoryResponseDto toResponseDto(Category category) {
        return mapper.map(category, CategoryResponseDto.class);
    }

    @Override
    public void toEntity(CategoryUpdateDto categoryUpdateDto, Category category) {
        mapper.map(categoryUpdateDto,category);
    }
}
