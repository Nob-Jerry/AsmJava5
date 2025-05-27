package org.asmjava5.service;

import org.asmjava5.data.dto.request.CartItemDtoRequest;
import org.asmjava5.data.dto.response.CartItemDtoResponse;

import java.util.List;

public interface CartItemService {
    List<CartItemDtoResponse> getCartItems(String username);
    CartItemDtoResponse getCartItem(String username, String productName);
    Boolean deleteCartItem(String username, String productName);
    Boolean deleteAllCartItem(String username);
    Boolean updateCartItem(CartItemDtoRequest cartItemDtoRequest);
    Boolean addCartItem(CartItemDtoRequest cartItemDtoRequest);

}
