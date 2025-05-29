package org.asmjava5.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asmjava5.common.ApiResponse;
import org.asmjava5.data.dto.request.CartItemDtoRequest;
import org.asmjava5.data.entity.CartItem;
import org.asmjava5.repository.CartItemRepository;
import org.asmjava5.service.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/cart-item")
@RequiredArgsConstructor
@Slf4j
public class CartItemController {
    private final CartItemService cartItemService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(@RequestParam("username") String username) throws SQLException {
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .status(200)
                            .message("Success")
                            .data(cartItemService.getCartItemList(username))
                            .build()
            );
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CartItemDtoRequest cartItemDtoRequest) throws SQLException {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .status(200)
                        .message("Success")
                        .data(cartItemService.addCartItem(cartItemDtoRequest))
                        .build()
        );
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody CartItemDtoRequest cartItemDtoRequest) throws SQLException {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .status(200)
                        .message("Success")
                        .data(cartItemService.updateCartItem(cartItemDtoRequest))
                        .build()
        );
    }

    @DeleteMapping("/delete-list")
    public ResponseEntity<?> deleteList(@RequestParam("userId") Long userId, @RequestParam("productIdList")List<Long> productIdList) throws SQLException {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .status(200)
                        .message("Success")
                        .data(cartItemService.deleteCartItemList(userId, productIdList))
                        .build()
        );
    }
}
