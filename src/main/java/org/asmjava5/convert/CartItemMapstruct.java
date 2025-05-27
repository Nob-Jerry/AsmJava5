package org.asmjava5.convert;

import org.asmjava5.data.dto.request.CartItemDtoRequest;
import org.asmjava5.data.dto.response.CartItemDtoResponse;
import org.asmjava5.data.entity.CartItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapstruct {
    CartItemDtoResponse toCartItemDtoResponse(CartItem cartItem);
    CartItem toCartItem(CartItemDtoRequest cartItemDtoRequest);
    List<CartItemDtoResponse> toCartItemResponseList(List<CartItem> cartItemList);
}
