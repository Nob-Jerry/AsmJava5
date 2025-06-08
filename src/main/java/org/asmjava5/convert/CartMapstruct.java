package org.asmjava5.convert;

import org.asmjava5.data.dto.request.CartDtoRequest;
import org.asmjava5.data.dto.request.CartItemDtoRequest;
import org.asmjava5.data.dto.response.CartDtoResponse;
import org.asmjava5.data.dto.response.CartItemDtoResponse;
import org.asmjava5.data.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(componentModel = "spring", uses = {CartItemMapstruct.class})
public interface CartMapstruct {
    Cart toCart(CartDtoRequest cartDtoRequest);

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "cart.cartItems", target = "cartItems")
    CartDtoResponse toCartDtoResponse(Cart cart);
}
