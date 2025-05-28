package org.asmjava5.service;

import org.asmjava5.data.dto.request.CartItemDtoRequest;
import org.asmjava5.data.dto.response.CartItemDtoResponse;

import java.sql.SQLException;
import java.util.List;

public interface CartItemService {
    List<CartItemDtoResponse> getCartItems(String username) throws SQLException;
    Boolean deleteCartItem(Long userId, Long productId);
    Boolean deleteCartItemList(Long userId, List<Long> productId);
    Boolean deleteAllCartItem(String username);
    Boolean updateCartItem(CartItemDtoRequest cartItemDtoRequest);
    Boolean addCartItem(CartItemDtoRequest cartItemDtoRequest);

}
