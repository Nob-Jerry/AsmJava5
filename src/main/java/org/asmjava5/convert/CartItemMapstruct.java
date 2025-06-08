package org.asmjava5.convert;

import org.asmjava5.data.dto.request.CartItemDtoRequest;
import org.asmjava5.data.dto.request.update.CartItemUpdateRequest;
import org.asmjava5.data.dto.response.CartItemDtoResponse;
import org.asmjava5.data.entity.CartItem;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring"
//        ,unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface CartItemMapstruct {
    @Mapping(source = "product.productId", target = "productId")
    @Mapping(source = "product.productName", target = "productName")
    @Mapping(source = "product.productPrice", target = "productPrice")
    @Mapping(source = "product.imageUrl", target = "productImage")
//    @BeanMapping(ignoreUnmappedSourceProperties = {"cart"})
    CartItemDtoResponse toCartItemDtoResponse(CartItem cartItem);
    List<CartItemDtoResponse> toCartItemResponseList(List<CartItem> cartItemList);
    CartItem toCartItem(CartItemDtoRequest cartItemDtoRequest);
}
