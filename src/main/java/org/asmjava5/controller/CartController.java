package org.asmjava5.controller;

import lombok.RequiredArgsConstructor;
import org.asmjava5.common.ApiResponse;
import org.asmjava5.data.dto.request.CartDtoRequest;
import org.asmjava5.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CartDtoRequest cartDtoRequest) {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .message("Save success")
                .build());
    }
}
