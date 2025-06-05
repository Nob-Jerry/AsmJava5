package org.asmjava5.controller;

import lombok.RequiredArgsConstructor;
import org.asmjava5.common.ApiResponse;
import org.asmjava5.data.dto.request.CartDtoRequest;
import org.asmjava5.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(cartService.createCart(userId))
                .message("Save success")
                .build());
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .data(cartService.getCart(userId))
                .build());
    }
}
