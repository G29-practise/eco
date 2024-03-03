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


@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDto>createCategory(@RequestBody @Valid CategoryCreateDto categoryCreateDto){
        CategoryResponseDto categoryResponseDto = categoryService.create(categoryCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<CategoryResponseDto>>getAllCategory(@RequestParam(required = false) String predicate, Pageable pageable){
        Page<CategoryResponseDto> categoryResponseDtoPage = categoryService.getAll(predicate, pageable);
        return ResponseEntity.ok(categoryResponseDtoPage);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryResponseDto>getCategory(@PathVariable String name){
        CategoryResponseDto categoryResponseDto = categoryService.get(name);
        return ResponseEntity.ok(categoryResponseDto);
    }

    @PutMapping("/{name}")
    public ResponseEntity<CategoryResponseDto>updateCategory(@PathVariable String name, @RequestBody CategoryUpdateDto categoryUpdateDto){
        CategoryResponseDto categoryResponseDto = categoryService.update(name,categoryUpdateDto);
        return ResponseEntity.ok(categoryResponseDto);
    }
    @DeleteMapping("/{name}")
    public void deleteCategory(@PathVariable String name){
        categoryService.delete(name);
    }
}
