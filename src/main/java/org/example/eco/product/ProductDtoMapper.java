package org.example.eco.product;

import lombok.RequiredArgsConstructor;
import org.example.eco.common.mapper.GenericMapper;
import org.example.eco.product.category.entity.Category;
import org.example.eco.product.dto.ProductCreateDto;
import org.example.eco.product.dto.ProductResponseDto;
import org.example.eco.product.dto.ProductUpdateDto;
import org.example.eco.product.entity.Product;
import org.example.eco.rating.entity.Rating;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductDtoMapper extends GenericMapper<Product, ProductCreateDto, ProductResponseDto, ProductUpdateDto> {
    private final ModelMapper mapper;

    @Override
    public Product toEntity(ProductCreateDto productCreateDto) {
        return mapper.map(productCreateDto, Product.class);
    }

    @Override
    public ProductResponseDto toResponseDto(Product product) {
        int rating = 0;
        Set<Rating> ratings = product.getRatings();
        if (ratings != null && !ratings.isEmpty()) {
            for (Rating r : ratings) {
                rating += r.getStars();
            }
            rating = rating / ratings.size();
        }
        Set<String> categories = product
                .getCategories()
                .stream()
                .map(Category::getName)
                .collect(Collectors.toSet());

        ProductResponseDto productResponseDto = mapper.map(product, ProductResponseDto.class);
        productResponseDto.setRating(rating);
        productResponseDto.setCategories(categories);
        return productResponseDto;
    }

    @Override
    public void toEntity(ProductUpdateDto productUpdateDto, Product product) {
        mapper.map(productUpdateDto, product);
    }
}
