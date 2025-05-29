package org.asmjava5.service;

import org.asmjava5.data.dto.request.CartItemDtoRequest;
import org.asmjava5.data.dto.response.CartItemDtoResponse;

import java.sql.SQLException;
import java.util.List;

public interface CartItemService {
    List<CartItemDtoResponse> getCartItemList(String username) throws SQLException;
    Boolean deleteCartItemList(Long userId, List<Long> productId) throws SQLException;
    Boolean updateCartItem(CartItemDtoRequest cartItemDtoRequest) throws SQLException;
    Boolean addCartItem(CartItemDtoRequest cartItemDtoRequest) throws SQLException;

}
