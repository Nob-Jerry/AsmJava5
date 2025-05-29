package org.asmjava5.service;

import org.asmjava5.data.dto.request.CartItemDtoRequest;
import org.asmjava5.data.dto.response.CartItemDtoResponse;

import java.sql.SQLException;
import java.util.List;

public interface CartItemService {
    List<CartItemDtoResponse> getCartItemList(String username);
    Boolean deleteCartItemList(Long userId, List<Long> productId);
    Boolean updateCartItem(CartItemDtoRequest cartItemDtoRequest);
    Boolean addCartItem(CartItemDtoRequest cartItemDtoRequest);
}
