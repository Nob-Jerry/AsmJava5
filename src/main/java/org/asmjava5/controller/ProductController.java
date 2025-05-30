package org.asmjava5.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asmjava5.common.ApiResponse;
import org.asmjava5.data.dto.request.ProductDtoRequest;
import org.asmjava5.data.dto.request.update.ProductUpdateRequest;
import org.asmjava5.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() throws SQLException {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .data(productService.getAllProducts())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) throws SQLException {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .data(productService.getProductById(id))
                .build());
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDtoRequest productDtoRequest) throws SQLException {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .data(productService.saveProduct(productDtoRequest))
                .build());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductUpdateRequest productUpdateRequest) throws SQLException {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .data(productService.updateProduct(productUpdateRequest))
                .build());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestParam("id") Long id) throws SQLException {
        productService.deleteProduct(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .message("Product deleted")
                .build());
    }

}
