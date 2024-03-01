package org.example.eco.productSet;

import lombok.RequiredArgsConstructor;
import org.example.eco.common.mapper.GenericMapper;
import org.example.eco.productSet.dto.ProductSetCreateDto;
import org.example.eco.productSet.dto.ProductSetResponseDto;
import org.example.eco.productSet.dto.ProductSetUpdateDto;
import org.example.eco.productSet.entity.ProductSet;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductSetDtoMapper extends GenericMapper<ProductSet, ProductSetCreateDto, ProductSetResponseDto, ProductSetUpdateDto> {

    private final ModelMapper mapper;

    @Override
    public ProductSet toEntity(ProductSetCreateDto productSetCreateDto) {
        return mapper.map(productSetCreateDto, ProductSet.class);
    }

    @Override
    public ProductSetResponseDto toResponseDto(ProductSet productSet) {
        return mapper.map(productSet, ProductSetResponseDto.class);
    }

    @Override
    public void toEntity(ProductSetUpdateDto productSetUpdateDto, ProductSet productSet) {
        mapper.map(productSetUpdateDto,productSet);
    }
}
