package org.asmjava5.convert;

import org.asmjava5.data.dto.request.OrderDetailDtoRequest;
import org.asmjava5.data.dto.request.update.OrderDetailUpdateRequest;
import org.asmjava5.data.dto.response.OrderDetailDtoResponse;
import org.asmjava5.data.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface OrderDetailMapstruct {
    @Mapping(source = "product.productId", target = "productId")
    @Mapping(source = "product.productName", target = "productName")
    OrderDetailDtoResponse toOrderDetailDtoResponse(OrderDetail orderDetail);
    List<OrderDetailDtoResponse> toOrderDetailDtoResponseList(List<OrderDetail> orderDetailList);

    @Mapping(source = "productId", target = "product.productId")
    OrderDetail toOrderDetail(OrderDetailDtoRequest orderDetailDtoRequest);
    OrderDetail toUpdateOrderDetail(OrderDetailUpdateRequest orderDetailUpdateRequest);

}
