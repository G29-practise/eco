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
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter
public class ProductService extends GenericService<Product,UUID, ProductCreateDto, ProductResponseDto, ProductUpdateDto> {

    private final ProductDtoMapper mapper;
    private final ProductRepository repository;
    private final Class<Product>entityClass = Product.class;
    private final CategoryRepository categoryRepository;

    @Override
    protected ProductResponseDto internalCreate(ProductCreateDto productCreateDto) {
        Product product = mapper.toEntity(productCreateDto);

        Set<Category> dtoCategoryNames = product.getCategories();

        Set<Category> categories = categoryRepository.findAllByNameIn(productCreateDto.getCategories());
        if(dtoCategoryNames.size() != categories.size()){
            Set<String> categoryNames = categories
                    .stream()
                    .map(Category::getName)
                    .collect(Collectors.toSet());

            dtoCategoryNames.removeAll(categoryNames);
            throw new EntityNotFoundException("Category with these names are not found.Categories: %s "
                    .formatted(dtoCategoryNames));
        }

        //setCategories(productCreateDto, product);


        product.setCategories(categories);
        product.setId(UUID.randomUUID());
        Product saved = repository.save(product);
        return mapper.toResponseDto(saved);

    }

    @Override
    protected ProductResponseDto internalUpdate(UUID uuid, ProductUpdateDto productUpdateDto) {
        Product product = repository.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException("Product with id: %s not fount".formatted(uuid)));
        mapper.toEntity(productUpdateDto,product);
        Product saved = repository.save(product);

        return mapper.toResponseDto(saved);
    }

    public ProductResponseDto getByTitle(String productTitle){
        Product product = repository
                .findByTitle(productTitle)
                .orElseThrow(
                        () -> new EntityNotFoundException("Product with name: %s not found".formatted(productTitle)));
        return mapper.toResponseDto(product);
    }
    private void setCategories(ProductCreateDto dto, Product product) {
        if (Objects.nonNull(dto.getCategories())) {
            Set<Category> categorySet = dto.getCategories()
                    .stream()
                    .map((catName) -> categoryRepository.findByName(catName)
                            .orElseThrow(EntityNotFoundException::new)
                    ).collect(Collectors.toSet());
            product.setCategories(categorySet);
        }
    }
}
