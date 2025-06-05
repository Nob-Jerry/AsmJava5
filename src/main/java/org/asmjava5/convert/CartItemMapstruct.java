package org.asmjava5.convert;

import org.asmjava5.data.dto.request.CartItemDtoRequest;
import org.asmjava5.data.dto.request.update.CartItemUpdateRequest;
import org.asmjava5.data.dto.response.CartItemDtoResponse;
import org.asmjava5.data.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapstruct {
    @Mapping(source = "product.productId", target = "productId")
    @Mapping(source = "product.productName", target = "productName")
    @Mapping(source = "product.productPrice", target = "productPrice")
    CartItemDtoResponse toCartItemDtoResponse(CartItem cartItem);
    List<CartItemDtoResponse> toCartItemResponseList(List<CartItem> cartItemList);
    CartItem toCartItem(CartItemDtoRequest cartItemDtoRequest);
    CartItem toCartItem(CartItemUpdateRequest cartItemUpdateRequest);
    CartItemDtoResponse toItemDtoResponse(CartItemUpdateRequest cartItemUpdateRequest);
}
