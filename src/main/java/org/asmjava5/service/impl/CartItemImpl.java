package org.asmjava5.service.impl;

import lombok.RequiredArgsConstructor;
import org.asmjava5.convert.CartItemMapstruct;
import org.asmjava5.data.dto.request.CartItemDtoRequest;
import org.asmjava5.data.dto.response.CartItemDtoResponse;
import org.asmjava5.repository.CartItemRepository;
import org.asmjava5.service.CartItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapstruct cartItemMapstruct;

    @Override
    public List<CartItemDtoResponse> getCartItems(String username) {
        var cartItemList = cartItemRepository.findByCart_User_Username(username);
        return cartItemMapstruct.toCartItemResponseList(cartItemList);
    }

    @Override
    public CartItemDtoResponse getCartItem(String username, String productName) {
        var cartItem = cartItemRepository.findByCart_User_UsernameAndProduct_ProductName(username, productName);
        return cartItemMapstruct.toCartItemDtoResponse(cartItem);
    }

    @Override
    public Boolean deleteCartItem(String username, String productName) {
        var cartItem = cartItemRepository.findByCart_User_UsernameAndProduct_ProductName(username, productName);
        if (cartItem != null) {
            cartItemRepository.delete(cartItem);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteAllCartItem(String username) {
        var cartItemList = cartItemRepository.findByCart_User_Username(username);
        if (cartItemList != null) {
            cartItemRepository.deleteAll(cartItemList);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateCartItem(CartItemDtoRequest cartItemDtoRequest) {
        var cartItem = cartItemMapstruct.toCartItem(cartItemDtoRequest);
        if (cartItemRepository.findByCart_User_UsernameAndProduct_ProductName(cartItem.getCart().getUser().getUsername(),cartItem.getProduct().getProductName()) != null) {
            cartItemRepository.save(cartItem);
            return true;
        }
        return false;
    }

    @Override
    public Boolean addCartItem(CartItemDtoRequest cartItemDtoRequest) {
        var cartItem = cartItemMapstruct.toCartItem(cartItemDtoRequest);
        if (cartItemRepository.findByCart_User_UsernameAndProduct_ProductName(cartItem.getCart().getUser().getUsername(),cartItem.getProduct().getProductName()) == null) {
            cartItemRepository.save(cartItem);
            return true;
        }
        return false;
    }
}
