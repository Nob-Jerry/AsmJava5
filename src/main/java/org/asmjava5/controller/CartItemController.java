package org.asmjava5.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asmjava5.common.ApiResponse;
import org.asmjava5.data.dto.request.CartItemDtoRequest;
import org.asmjava5.data.dto.request.update.CartItemUpdateRequest;
import org.asmjava5.data.entity.CartItem;
import org.asmjava5.repository.CartItemRepository;
import org.asmjava5.service.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart-item")
@RequiredArgsConstructor
@Slf4j
public class CartItemController {
    private final CartItemService cartItemService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(@RequestParam("username") String username) {
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .status(200)
                            .message("Success")
                            .data(cartItemService.getCartItemList(username))
                            .build()
            );
    }

//    @PostMapping("/save")
//    public ResponseEntity<?> save(@RequestBody CartItemDtoRequest cartItemDtoRequest)  {
//        return ResponseEntity.ok(
//                ApiResponse.builder()
//                        .status(200)
//                        .message("Success")
//                        .data(cartItemService.addCartItem(cartItemDtoRequest))
//                        .build()
//        );
//    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody CartItemUpdateRequest cartItemUpdateRequest)  {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .status(200)
                        .message("Success")
                        .data(cartItemService.updateCartItem(cartItemUpdateRequest))
                        .build()
        );
    }

    @DeleteMapping("/delete-list")
    public ResponseEntity<?> deleteList(@RequestParam("userId") Long userId, @RequestParam("productIdList")List<Long> productIdList) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .status(200)
                        .message("Success")
                        .data(cartItemService.deleteCartItemList(userId, productIdList))
                        .build()
        );
    }
}
