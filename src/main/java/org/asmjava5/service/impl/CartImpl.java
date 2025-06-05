package org.asmjava5.service.impl;

import lombok.RequiredArgsConstructor;
import org.asmjava5.convert.CartMapstruct;
import org.asmjava5.data.dto.request.CartDtoRequest;
import org.asmjava5.data.dto.response.CartDtoResponse;
import org.asmjava5.data.entity.Cart;
import org.asmjava5.enums.ErrorCode;
import org.asmjava5.exception.AppException;
import org.asmjava5.repository.CartRepository;
import org.asmjava5.repository.UserRepository;
import org.asmjava5.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CartImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartMapstruct cartMapstruct;


    @Override
    public Boolean createCart(Long userId) {
        Cart save = new Cart();
        save.setCreateAt(new Date());
        save.setUser(userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_EMPTY)));
        try {
            cartRepository.save(save);
            return true;
        } catch (AppException e) {
            throw new AppException(ErrorCode.FAIL_TO_SAVE_UPDATE);
        }
    }

    @Override
    @Transactional
    public CartDtoResponse getCart(Long userId) {
        Cart cart = cartRepository.findCartByUser_UserId(userId);
        if (cart == null) {
            throw new AppException(ErrorCode.T_EMPTY);
        }else{
            return cartMapstruct.toCartDtoResponse(cart);
        }
    }
}
