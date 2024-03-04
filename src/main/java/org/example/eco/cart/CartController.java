package org.example.eco.cart;

import lombok.RequiredArgsConstructor;
import org.example.eco.cart.dto.CartCreateDto;
import org.example.eco.cart.dto.CartResponseDto;
import org.example.eco.cart.dto.CartUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
//    @PreAuthorize("hasAnyAuthority('cart:create')")
    @PostMapping
    public ResponseEntity<CartResponseDto> create(@RequestBody CartCreateDto cartCreateDto){
        CartResponseDto cartResponseDto = cartService.create(cartCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartResponseDto);
    }
//    @PreAuthorize("hasAnyAuthority('commit:read')")

    @GetMapping("/{id}")
    public ResponseEntity<CartResponseDto> getId(@PathVariable UUID id){
        CartResponseDto cartResponseDto = cartService.get(id);
        return ResponseEntity.ok(cartResponseDto);
    }
//    @PreAuthorize("hasAnyAuthority('commit:read')")

    @GetMapping
    public ResponseEntity<Page<CartResponseDto>> getAll(Pageable pageable, @RequestParam(required = false) String predicate){
        Page<CartResponseDto> all = cartService.getAll(predicate, pageable);
        return ResponseEntity.ok(all);
    }
    @PreAuthorize("hasAnyAuthority('commit:update')")

    @PutMapping("/{id}")
    public ResponseEntity<CartResponseDto> update(@PathVariable UUID id, @RequestBody CartUpdateDto updateDTO){
        CartResponseDto update = cartService.update(id, updateDTO);
        return ResponseEntity.ok(update);
    }
    @PreAuthorize("hasAnyAuthority('commit:delete')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        cartService.delete(id);
    }
}
