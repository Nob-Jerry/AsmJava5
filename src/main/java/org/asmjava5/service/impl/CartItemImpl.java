package org.asmjava5.service.impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.asmjava5.convert.CartItemMapstruct;
import org.asmjava5.data.dto.request.CartItemDtoRequest;
import org.asmjava5.data.dto.request.update.CartItemUpdateRequest;
import org.asmjava5.data.dto.response.CartItemDtoResponse;
import org.asmjava5.data.entity.CartItem;
import org.asmjava5.enums.ErrorCode;
import org.asmjava5.exception.AppException;
import org.asmjava5.repository.CartItemRepository;
import org.asmjava5.service.CartItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CartItemImpl implements CartItemService {

    private final EntityManager entityManager;
    private final CartItemRepository cartItemRepository;
    private final CartItemMapstruct cartItemMapstruct;

    @Override
    public List<CartItemDtoResponse> getCartItemList(String username) {
        var cartItemList = cartItemRepository.findByCart_User_Username(username);
        if (cartItemList.isEmpty()) throw new AppException(ErrorCode.FAIL_GET_LIST);
        return cartItemMapstruct.toCartItemResponseList(cartItemList);
    }

    @Override
    @Transactional
    public Boolean deleteCartItemList(Long userId, List<Long> productId) {
        if (!cartItemRepository.existsById(userId) && productId == null ) throw new AppException(ErrorCode.FAIL_DELETE);
        cartItemRepository.deleteByCart_User_UserIdAndProduct_ProductIdIn(userId, productId);
        return true;
    }

    @Override
    @Transactional
    public CartItemDtoResponse updateCartItem(CartItemUpdateRequest cartItemUpdateRequest) {
        CartItem check = cartItemRepository.findByCart_CartIdAndProduct_ProductId(cartItemUpdateRequest.getCartId(), cartItemUpdateRequest.getProductId());
        if (check == null){
            Integer insertRow = cartItemRepository.createCartItem(cartItemUpdateRequest.getCartId(), cartItemUpdateRequest.getProductId(), cartItemUpdateRequest.getQuantity());
            check = cartItemRepository.findByCart_CartIdAndProduct_ProductId(
                    cartItemUpdateRequest.getCartId(),
                    cartItemUpdateRequest.getProductId()
            );
            entityManager.flush();
            entityManager.clear();
        }else {
            Integer rowUpdate = cartItemRepository.updateCartItem(cartItemUpdateRequest);
            check = cartItemRepository.findByCart_CartIdAndProduct_ProductId(
                    cartItemUpdateRequest.getCartId(),
                    cartItemUpdateRequest.getProductId()
            );            entityManager.flush();
            entityManager.clear();
            if (rowUpdate == 0) throw new AppException(ErrorCode.FAIL_TO_SAVE_UPDATE);
        }
        return cartItemMapstruct.toCartItemDtoResponse(check);
    }

    @Override
    @Transactional
    public Boolean addCartItem(CartItemDtoRequest cartItemDtoRequest) {
        CartItem cartItem = cartItemMapstruct.toCartItem(cartItemDtoRequest);
        CartItem check = cartItemRepository.findById(cartItem.getCartItemId()).orElseThrow(()-> new AppException(ErrorCode.FAIL_TO_SAVE_UPDATE));
        if (check != null) throw new AppException(ErrorCode.FAIL_TO_SAVE_UPDATE);
        cartItemRepository.saveAndFlush(cartItem);
        return true;
    }
}
