package org.asmjava5.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asmjava5.common.ApiResponse;
import org.asmjava5.data.entity.CartItem;
import org.asmjava5.repository.CartItemRepository;
import org.asmjava5.service.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart-item")
@RequiredArgsConstructor
@Slf4j
public class CartItemController {
    private final CartItemService cartItemService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(@RequestParam("username") String username) {
        try {
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .status(200)
                            .message("Success")
                            .data(cartItemService.getCartItems(username))
                            .build()
            );
        } catch (Exception e) {
            log.error("Error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body( ApiResponse.builder()
                            .status(500)
                            .message(e.getMessage())
                            .build());
        }
    }

}
