package org.asmjava5.controller;

import lombok.RequiredArgsConstructor;
import org.asmjava5.common.ApiResponse;
import org.asmjava5.data.dto.request.CategoryDtoRequest;
import org.asmjava5.data.dto.request.update.CategoryUpdateRequest;
import org.asmjava5.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .data(categoryService.getCategories())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .data(categoryService.getCategoryById(id))
                .build());
    }


    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CategoryDtoRequest categoryDtoRequest) {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .data(categoryService.addCategory(categoryDtoRequest))
                .build());
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .data(categoryService.updateCategory(categoryUpdateRequest))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .data(categoryService.deleteCategoryById(id))
                .build());
    }
}
