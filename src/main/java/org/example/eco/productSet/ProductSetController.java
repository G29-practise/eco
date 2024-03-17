package org.example.eco.productSet;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.eco.common.App;
import org.example.eco.productSet.dto.ProductSetCreateDto;
import org.example.eco.productSet.dto.ProductSetResponseDto;
import org.example.eco.productSet.dto.ProductSetUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

import static org.example.eco.productSet.ProductSetController.BATH_URL;

@RestController
@RequestMapping(App.BASE_PATH + BATH_URL)
@RequiredArgsConstructor
public class ProductSetController {

    public static final String BATH_URL = "/productSet";

    private final ProductSetService productSetService;

    @PostMapping("/create")
    public ResponseEntity<ProductSetResponseDto>createProductSet(@RequestBody @Valid ProductSetCreateDto productSetCreateDto) throws IOException {
        ProductSetResponseDto productSetResponseDto = productSetService.create(productSetCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productSetResponseDto);
    }

    @GetMapping("/getAllSet")
    public ResponseEntity<Page<ProductSetResponseDto>>getAllProductSet(@RequestParam(required = false) String predicate, Pageable pageable){
        Page<ProductSetResponseDto> productSetResponseDtoPage = productSetService.getAll(predicate, pageable);
        return ResponseEntity.ok(productSetResponseDtoPage);
    }

    @GetMapping("/getByProductSetId{id}")
    public ResponseEntity<ProductSetResponseDto>getProductSet(@PathVariable UUID id){
        ProductSetResponseDto productSetResponseDto = productSetService.get(id);
        return ResponseEntity.ok(productSetResponseDto);
    }

    @PutMapping("/updateByProductSetId{id}")
    public ResponseEntity<ProductSetResponseDto>updateProductSet(@PathVariable UUID id, @RequestBody ProductSetUpdateDto productSetUpdateDto){
        ProductSetResponseDto productSetResponseDto = productSetService.update(id,productSetUpdateDto);
        return ResponseEntity.ok(productSetResponseDto);
    }

    @DeleteMapping("/deleteByProductSetId{id}")
    public ResponseEntity<?> deleteProductSet(@PathVariable UUID id){
        productSetService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
