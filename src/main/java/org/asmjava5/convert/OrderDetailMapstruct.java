package org.asmjava5.convert;

import org.asmjava5.data.dto.request.OrderDetailDtoRequest;
import org.asmjava5.data.dto.request.update.OrderDetailUpdateRequest;
import org.asmjava5.data.dto.response.OrderDetailDtoResponse;
import org.asmjava5.data.entity.OrderDetail;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface OrderDetailMapstruct {
    OrderDetailDtoResponse toOrderDetailDtoResponse(OrderDetail orderDetail);
    List<OrderDetailDtoResponse> toOrderDetailDtoResponseList(List<OrderDetail> orderDetailList);
    OrderDetail toOrderDetail(OrderDetailDtoRequest orderDetailDtoRequest);
    OrderDetail toUpdateOrderDetail(OrderDetailUpdateRequest orderDetailUpdateRequest);

}
