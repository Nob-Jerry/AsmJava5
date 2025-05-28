package org.asmjava5.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asmjava5.common.ApiResponse;
import org.asmjava5.data.dto.request.ProductDtoRequest;
import org.asmjava5.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .data(productService.getAllProducts())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .data(productService.getProductById(id))
                .build());
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDtoRequest productDtoRequest) {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .data(productService.saveProduct(productDtoRequest))
                .build());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDtoRequest productDtoRequest) {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .data(productService.updateProduct(productDtoRequest))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .message("Product deleted")
                .build());
    }

}
