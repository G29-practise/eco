package org.example.eco.product.category;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.eco.common.App;
import org.example.eco.product.category.dto.CategoryCreateDto;
import org.example.eco.product.category.dto.CategoryResponseDto;
import org.example.eco.product.category.dto.CategoryUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.example.eco.product.category.CategoryController.BATH_URL;


@RestController
@RequestMapping(App.BASE_PATH + BATH_URL)
@RequiredArgsConstructor
public class CategoryController {

    public static final String BATH_URL = "/category";

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody @Valid CategoryCreateDto categoryCreateDto) throws IOException {
        CategoryResponseDto categoryResponseDto = categoryService.create(categoryCreateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryResponseDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<CategoryResponseDto>> getAllCategory(@RequestParam(required = false) String predicate, Pageable pageable) {
        Page<CategoryResponseDto> categoryResponseDtoPage = categoryService.getAll(predicate, pageable);
        return ResponseEntity.ok(categoryResponseDtoPage);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<CategoryResponseDto> getCategory(@PathVariable String name) {
        CategoryResponseDto categoryResponseDto = categoryService.get(name);
        return ResponseEntity.ok(categoryResponseDto);
    }

    @PutMapping("/updateByName/{name}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable String name, @RequestBody CategoryUpdateDto categoryUpdateDto) {
        CategoryResponseDto categoryResponseDto = categoryService.update(name, categoryUpdateDto);
        return ResponseEntity.ok(categoryResponseDto);
    }

    @DeleteMapping("/deleteByName/{name}")
    public ResponseEntity<?> deleteCategory(@PathVariable String name) {
        categoryService.delete(name);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
