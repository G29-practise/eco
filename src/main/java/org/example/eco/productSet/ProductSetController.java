package org.example.eco.productSet;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.eco.productSet.dto.ProductSetCreateDto;
import org.example.eco.productSet.dto.ProductSetResponseDto;
import org.example.eco.productSet.dto.ProductSetUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/productSet")
@RequiredArgsConstructor
public class ProductSetController {

    private final ProductSetService productSetService;

    @PostMapping
    public ResponseEntity<ProductSetResponseDto>createProductSet(@RequestBody @Valid ProductSetCreateDto productSetCreateDto){
        ProductSetResponseDto productSetResponseDto = productSetService.internalCreate(productSetCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productSetResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<ProductSetResponseDto>>getAllProductSet(@RequestParam(required = false) String predicate, Pageable pageable){
        Page<ProductSetResponseDto> productSetResponseDtoPage = productSetService.getAll(predicate, pageable);
        return ResponseEntity.ok(productSetResponseDtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductSetResponseDto>getProductSet(@PathVariable UUID id){
        ProductSetResponseDto productSetResponseDto = productSetService.get(id);
        return ResponseEntity.ok(productSetResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductSetResponseDto>updateProductSet(@PathVariable UUID id, @RequestBody ProductSetUpdateDto productSetUpdateDto){
        ProductSetResponseDto productSetResponseDto = productSetService.internalUpdate(id,productSetUpdateDto);
        return ResponseEntity.ok(productSetResponseDto);
    }
    @DeleteMapping("/{id}")
    public void deleteProductSet(@PathVariable UUID id){
        productSetService.delete(id);
    }
}
