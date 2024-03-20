package org.example.eco.wishlist;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.eco.wishlist.dto.WishlistCreateDto;
import org.example.eco.wishlist.dto.WishlistResponseDto;
import org.example.eco.wishlist.dto.WishlistUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;
    @PostMapping
    public ResponseEntity<WishlistResponseDto> createWishlist(@RequestBody @Valid WishlistCreateDto wishlistCreateDto){
        WishlistResponseDto wishlistResponseDto = wishlistService.internalCreate(wishlistCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(wishlistResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<WishlistResponseDto>>getAllWishlist(@RequestParam(required = false) String predicate, Pageable pageable){
        Page<WishlistResponseDto> wishlistResponseDtoPage = wishlistService.getAll(predicate, pageable);
        return ResponseEntity.ok(wishlistResponseDtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishlistResponseDto>getWishlist(@PathVariable UUID id){
        WishlistResponseDto wishlistResponseDto = wishlistService.get(id);
        return ResponseEntity.ok(wishlistResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WishlistResponseDto>updateWishlist(@PathVariable UUID id, @RequestBody WishlistUpdateDto wishlistUpdateDto){
        WishlistResponseDto wishlistResponseDto = wishlistService.internalUpdate(id, wishlistUpdateDto);
        return ResponseEntity.ok(wishlistResponseDto);
    }
    @DeleteMapping("/{id}")
    public void deleteWishlist(@PathVariable UUID id){
        wishlistService.delete(id);
    }
    @PostMapping( ("/{wishlistId}/add-product/{productId}"))
    public ResponseEntity<WishlistResponseDto> addProduct(@PathVariable UUID wishlistId,
                                                        @PathVariable UUID productId) {
        WishlistResponseDto wishlistResponseDto = wishlistService.addProduct(wishlistId, productId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(wishlistResponseDto);

    }

}
