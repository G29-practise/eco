package org.example.eco.product;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.eco.common.service.GenericService;
import org.example.eco.product.category.CategoryRepository;
import org.example.eco.product.category.entity.Category;
import org.example.eco.product.dto.ProductCreateDto;
import org.example.eco.product.dto.ProductResponseDto;
import org.example.eco.product.dto.ProductUpdateDto;
import org.example.eco.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter
public class ProductService extends GenericService<Product, UUID, ProductCreateDto, ProductResponseDto, ProductUpdateDto> {

    private final ProductDtoMapper mapper;
    private final ProductRepository repository;
    private final Class<Product> entityClass = Product.class;
    private final CategoryRepository categoryRepository;

    @Override
    protected ProductResponseDto internalCreate(ProductCreateDto productCreateDto) throws IOException {
        Product product = mapper.toEntity(productCreateDto);

        MultipartFile image = productCreateDto.getImage();
        String fileName = UUID.randomUUID() + image.getOriginalFilename();
        byte[] bytes = image.getBytes();
        String base64Img = Base64.getEncoder().encodeToString(bytes);
        product.setFileName(fileName);
        product.setContentType(image.getContentType());
        product.setImg(base64Img);

        Set<String> dtoCategoryNames = productCreateDto.getCategories();
        Set<Category> categories = categoryRepository.findAllByNameIn(dtoCategoryNames);

        if (dtoCategoryNames.size() != categories.size()) {
            Set<String> categoryNames = categories
                    .stream()
                    .map(Category::getName)
                    .collect(Collectors.toSet());

            dtoCategoryNames.removeAll(categoryNames);
            throw new EntityNotFoundException("Category with these names are not found.Categories: %s "
                    .formatted(dtoCategoryNames));
        }

        product.setCategories(categories);
        product.setId(UUID.randomUUID());
        Product saved = repository.save(product);
        return mapper.toResponseDto(saved);

    }

    @Override
    protected ProductResponseDto internalUpdate(UUID uuid, ProductUpdateDto productUpdateDto) {
        Product product = repository.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException("Product with id: %s not fount".formatted(uuid)));
        mapper.toEntity(productUpdateDto, product);
        Product saved = repository.save(product);

        return mapper.toResponseDto(saved);
    }

    public ProductResponseDto getByTitle(String productTitle) {
        Product product = repository
                .findByTitle(productTitle)
                .orElseThrow(
                        () -> new EntityNotFoundException("Product with name: %s not found".formatted(productTitle)));
        return mapper.toResponseDto(product);
    }

    public Page<ProductResponseDto> getRecentlyAdded(Pageable pageable) {
        Page<Product> recentlyAdded = repository.findRecentlyAdded(pageable);
        return recentlyAdded.map(mapper::toResponseDto);
    }

    public Page<ProductResponseDto> getTopRated(Pageable pageable) {
        Page<Product> topRatedProducts = repository.findTopRated(pageable);
        return topRatedProducts.map(mapper::toResponseDto);
    }
}
