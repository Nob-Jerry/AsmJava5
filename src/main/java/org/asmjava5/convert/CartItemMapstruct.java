package org.asmjava5.convert;

import org.asmjava5.data.dto.request.CartItemDtoRequest;
import org.asmjava5.data.dto.request.update.CartItemUpdateRequest;
import org.asmjava5.data.dto.response.CartItemDtoResponse;
import org.asmjava5.data.entity.CartItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapstruct {
    CartItemDtoResponse toCartItemDtoResponse(CartItem cartItem);
    List<CartItemDtoResponse> toCartItemResponseList(List<CartItem> cartItemList);
    CartItem toCartItem(CartItemDtoRequest cartItemDtoRequest);
    CartItem toUpdateCartItem(CartItemUpdateRequest cartItemUpdateRequest);
}
