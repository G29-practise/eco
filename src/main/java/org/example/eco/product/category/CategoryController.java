package org.example.eco.product.category;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.eco.product.category.dto.CategoryCreateDto;
import org.example.eco.product.category.dto.CategoryResponseDto;
import org.example.eco.product.category.dto.CategoryUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDto>createCategory(@RequestBody @Valid CategoryCreateDto categoryCreateDto){
        CategoryResponseDto categoryResponseDto = categoryService.internalCreate(categoryCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<CategoryResponseDto>>getAllCategory(@RequestParam(required = false) String predicate, Pageable pageable){
        Page<CategoryResponseDto> categoryResponseDtoPage = categoryService.getAll(predicate, pageable);
        return ResponseEntity.ok(categoryResponseDtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto>getCategory(@PathVariable UUID id){
        CategoryResponseDto categoryResponseDto = categoryService.get(id);
        return ResponseEntity.ok(categoryResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto>updateCategory(@PathVariable UUID id, @RequestBody CategoryUpdateDto categoryUpdateDto){
        CategoryResponseDto categoryResponseDto = categoryService.internalUpdate(id,categoryUpdateDto);
        return ResponseEntity.ok(categoryResponseDto);
    }
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable UUID id){
        categoryService.delete(id);
    }
}
