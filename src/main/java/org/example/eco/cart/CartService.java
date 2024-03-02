package org.example.eco.cart;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.eco.cart.dto.CartCreateDto;
import org.example.eco.cart.dto.CartResponseDto;
import org.example.eco.cart.dto.CartUpdateDto;
import org.example.eco.cart.entity.Cart;
import org.example.eco.common.service.GenericService;
import org.example.eco.productSet.ProductSetRepository;
import org.example.eco.productSet.entity.ProductSet;
import org.example.eco.user.UserRepository;
import org.example.eco.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
public class CartService extends GenericService<Cart, UUID, CartCreateDto, CartResponseDto, CartUpdateDto> {
    private final CartRepositoriy repository;
    private final Class<Cart> entityClass= Cart.class;
    private final CartDtoMapper mapper;
    private final UserRepository userRepository;
    private final ProductSetRepository productSetRepository;

    @Override
    protected CartResponseDto internalCreate(CartCreateDto cartCreateDto) {
        Cart entity = mapper.toEntity(cartCreateDto);
        User user = userRepository.findById(cartCreateDto.getUser_Id()).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + cartCreateDto.getUser_Id()));
        
        Set<ProductSet> productSets = new HashSet<>();
        for (UUID productId : cartCreateDto.getProductSet_Id()) {
            ProductSet productSet = getProductSetRepository().findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));
            productSets.add(productSet);
        }
        
        entity.setId(UUID.randomUUID());
        entity.setUser(user);
        entity.setProductSets(productSets);

        return mapper.toResponseDto(entity);
    }

    @Override
    protected CartResponseDto internalUpdate(UUID uuid, CartUpdateDto cartUpdateDto) {
        Cart cart = repository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        mapper.toEntity(cartUpdateDto,cart);
        repository.save(cart);

        return mapper.toResponseDto(cart);
    }
}
