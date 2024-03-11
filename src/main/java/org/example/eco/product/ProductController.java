package org.example.eco.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.eco.product.dto.ProductCreateDto;
import org.example.eco.product.dto.ProductResponseDto;
import org.example.eco.product.dto.ProductUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Valid ProductCreateDto productCreateDto) throws IOException {
        ProductResponseDto productResponseDto = productService.internalCreate(productCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>>getAllProduct(@RequestParam(required = false) String predicate, Pageable pageable){
        Page<ProductResponseDto> productResponseDtoPage = productService.getAll(predicate, pageable);
        return ResponseEntity.ok(productResponseDtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto>getProduct(@PathVariable UUID id){
        ProductResponseDto productResponseDto = productService.get(id);
        return ResponseEntity.ok(productResponseDto);
    }

    @GetMapping("/{productTitle}")
    public ResponseEntity<ProductResponseDto>getProduct(@PathVariable String productTitle){
        ProductResponseDto productResponseDto = productService.getByTitle(productTitle);
        return ResponseEntity.ok(productResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto>updateProduct(@PathVariable UUID id, @RequestBody ProductUpdateDto productUpdateDto){
        ProductResponseDto productResponseDto = productService.internalUpdate(id, productUpdateDto);
        return ResponseEntity.ok(productResponseDto);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable UUID id){
        productService.delete(id);
    }
}
