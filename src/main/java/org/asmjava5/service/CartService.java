package org.asmjava5.service;

import org.asmjava5.data.dto.request.CartDtoRequest;
import org.asmjava5.data.dto.response.CartDtoResponse;
import org.asmjava5.data.entity.Cart;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    Boolean createCart(Long userId);
    CartDtoResponse getCart(Long userId);
}
