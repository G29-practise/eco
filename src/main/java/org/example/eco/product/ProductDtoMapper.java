package org.example.eco.product;
import lombok.RequiredArgsConstructor;
import org.example.eco.common.mapper.GenericMapper;
import org.example.eco.product.dto.ProductCreateDto;
import org.example.eco.product.dto.ProductResponseDto;
import org.example.eco.product.dto.ProductUpdateDto;
import org.example.eco.product.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductDtoMapper extends GenericMapper<Product, ProductCreateDto, ProductResponseDto, ProductUpdateDto> {
    private final ModelMapper mapper;
    @Override
    public Product toEntity(ProductCreateDto productCreateDto) {
        return mapper.map(productCreateDto,Product.class);
    }

    @Override
    public ProductResponseDto toResponseDto(Product product) {
        return mapper.map(product, ProductResponseDto.class);
    }

    @Override
    public void toEntity(ProductUpdateDto productUpdateDto, Product product) {
        mapper.map(productUpdateDto,product);
    }
}
