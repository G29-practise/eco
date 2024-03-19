package org.example.eco.wishlist;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.eco.common.service.GenericService;
import org.example.eco.product.ProductRepository;
import org.example.eco.product.entity.Product;
import org.example.eco.user.UserRepository;
import org.example.eco.user.entity.User;
import org.example.eco.wishlist.dto.WishlistCreateDto;
import org.example.eco.wishlist.dto.WishlistResponseDto;
import org.example.eco.wishlist.dto.WishlistUpdateDto;
import org.example.eco.wishlist.entity.Wishlist;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class WishlistService extends GenericService<Wishlist, UUID, WishlistCreateDto, WishlistResponseDto, WishlistUpdateDto> {
    private final WishlistRepository repository;
    private final Class<Wishlist> entityClass = Wishlist.class;
    private final WishlistDtoMapper mapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    @Override
    protected WishlistResponseDto internalCreate(WishlistCreateDto wishlistCreateDto){
        Wishlist wishlist = mapper.toEntity(wishlistCreateDto);
        UUID userId = wishlistCreateDto.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        wishlist.setId(UUID.randomUUID());
        wishlist.setUser(user);

        userRepository.save(user);

        Wishlist saved = repository.save(wishlist);
        return mapper.toResponseDto(saved);
    }

    @Override
    protected WishlistResponseDto internalUpdate(UUID uuid, WishlistUpdateDto wishlistUpdateDto) {
        Wishlist wishlist = repository.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException("Wishlist not found"));
        mapper.toEntity(wishlistUpdateDto,wishlist);
        Wishlist saved = repository.save(wishlist);

        return mapper.toResponseDto(saved);
    }
    @Transactional
    public WishlistResponseDto addProduct(UUID wishlistId, UUID productId) {
        Set<Product>wishlistProducts = new HashSet<>();
        Wishlist wishlist = repository.findById(wishlistId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();
        wishlistProducts.add(product);

        wishlist.setProducts(wishlistProducts);
            Wishlist saved = repository.save(wishlist);
            return mapper.toResponseDto(saved);
        }
}
