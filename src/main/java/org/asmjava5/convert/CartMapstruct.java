package org.asmjava5.convert;

import org.asmjava5.data.dto.request.CartDtoRequest;
import org.asmjava5.data.dto.request.CartItemDtoRequest;
import org.asmjava5.data.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapstruct {
    Cart toCart(CartDtoRequest cartDtoRequest);
}
