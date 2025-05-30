package org.asmjava5.service;

import org.asmjava5.data.dto.request.CartDtoRequest;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    Boolean createCart(CartDtoRequest cartDtoRequest);
}
