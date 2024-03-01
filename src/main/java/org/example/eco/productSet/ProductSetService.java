package org.example.eco.productSet;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.eco.common.service.GenericService;
import org.example.eco.product.ProductRepository;
import org.example.eco.product.entity.Product;
import org.example.eco.productSet.dto.ProductSetCreateDto;
import org.example.eco.productSet.dto.ProductSetResponseDto;
import org.example.eco.productSet.dto.ProductSetUpdateDto;
import org.example.eco.productSet.entity.ProductSet;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
public class ProductSetService extends GenericService<ProductSet, UUID, ProductSetCreateDto, ProductSetResponseDto, ProductSetUpdateDto> {
    private final ProductSetRepository repository;
    private final Class<ProductSet> entityClass = ProductSet.class;
    private final ProductSetDtoMapper mapper;
    private final ProductRepository productRepository;
    //private final CartRepository cartRepository;

    @Override
    protected ProductSetResponseDto internalCreate(ProductSetCreateDto createDto) {
        ProductSet productSet = mapper.toEntity(createDto);
        setProduct(createDto, productSet);
        //setCart(createDto, productSet);
        return mapper.toResponseDto(repository.save(productSet));
    }

    @Override
    protected ProductSetResponseDto internalUpdate(UUID uuid, ProductSetUpdateDto updateDto) {
        ProductSet productSet = repository.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException("ProductSet with id: %s not fount".formatted(uuid)));
        mapper.toEntity(updateDto,productSet);
        ProductSet saved = repository.save(productSet);

        return mapper.toResponseDto(saved);
    }

    private void setProduct(ProductSetCreateDto createDto, ProductSet productSet) {
        Product product = productRepository.findById(createDto.getProductId()).orElseThrow(EntityNotFoundException::new);
        productSet.setProductId(product);
    }

   /* private void setCart(ProductSetCreateDto createDto, ProductSet productSet) {
        Cart cart = cartRepository.findById(createDto.getCart().getId()).orElseThrow(EntityNotFoundException::new);
        productSet.setCart(cart);
    }*/

}
