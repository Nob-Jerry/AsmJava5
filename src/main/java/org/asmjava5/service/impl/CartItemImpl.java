package org.asmjava5.service.impl;

import lombok.RequiredArgsConstructor;
import org.asmjava5.convert.CartItemMapstruct;
import org.asmjava5.data.dto.request.CartItemDtoRequest;
import org.asmjava5.data.dto.response.CartItemDtoResponse;
import org.asmjava5.enums.ErrorCode;
import org.asmjava5.exception.AppException;
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
    public List<CartItemDtoResponse> getCartItems(String username)throws RuntimeException {
        try {
            var cartItemList = cartItemRepository.findByCart_User_Username(username);
            return cartItemMapstruct.toCartItemResponseList(cartItemList);
        } catch (RuntimeException e) {
            throw new AppException(ErrorCode.BAD_SQL);
        }
    }

    @Override
    public Boolean deleteCartItem(Long userId, Long productId) {
        try{
            cartItemRepository.deleteByCart_User_UserIdAndProduct_ProductId(userId, productId);
            return true;
        } catch (RuntimeException e) {
            throw new AppException(ErrorCode.BAD_SQL);
        }
    }

    @Override
    public Boolean deleteCartItemList(Long userId, List<Long> productId) {
        try{
            cartItemRepository.deleteByCart_User_UserIdAndProduct_ProductIdIn(userId, productId);
            return true;
        } catch (RuntimeException e) {
            throw new AppException(ErrorCode.BAD_SQL);
        }
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
        try{
            var cartItem = cartItemMapstruct.toCartItem(cartItemDtoRequest);
            var check = cartItemRepository.findById(cartItem.getCartItemId());
            if(check != null) {
                cartItemRepository.saveAndFlush(cartItem);
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            throw new AppException(ErrorCode.BAD_SQL);
        }
    }

    @Override
    public Boolean addCartItem(CartItemDtoRequest cartItemDtoRequest) {
        try{
            var cartItem = cartItemMapstruct.toCartItem(cartItemDtoRequest);
            var check = cartItemRepository.findById(cartItem.getCartItemId());
            if(check == null) {
                cartItemRepository.saveAndFlush(cartItem);
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            throw new AppException(ErrorCode.BAD_SQL);
        }
    }
}
