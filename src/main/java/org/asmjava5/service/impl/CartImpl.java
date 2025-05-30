package org.asmjava5.service.impl;

import lombok.RequiredArgsConstructor;
import org.asmjava5.convert.CartMapstruct;
import org.asmjava5.data.dto.request.CartDtoRequest;
import org.asmjava5.enums.ErrorCode;
import org.asmjava5.exception.AppException;
import org.asmjava5.repository.CartRepository;
import org.asmjava5.service.CartService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartMapstruct cartMapstruct;

    @Override
    public Boolean createCart(CartDtoRequest cartDtoRequest) {
        var cart = cartMapstruct.toCart(cartDtoRequest);
        if (cart != null) {
             cartRepository.save(cart);
             return true;
        }else {
            throw new AppException(ErrorCode.FAIL_TO_SAVE_UPDATE);
        }
    }
}
