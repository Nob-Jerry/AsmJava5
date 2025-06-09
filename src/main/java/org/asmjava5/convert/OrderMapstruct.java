package org.asmjava5.convert;

import org.asmjava5.data.dto.request.OrderDtoRequest;
import org.asmjava5.data.dto.request.update.OrderUpdateRequest;
import org.asmjava5.data.dto.response.OrderDtoResponse;
import org.asmjava5.data.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderDetailMapstruct.class})
public interface OrderMapstruct {

    @Mapping(source = "status", target = "status")
    @Mapping(source = "user.userId",target = "userId")
    @Mapping(source = "user.username",target = "username")
    @Mapping(source = "order.orderDetails", target = "orderDetails")
    OrderDtoResponse toOrderDtoResponse(Order order);

    List<OrderDtoResponse> toOrderDtoResponseList(List<Order> orders);

    @Mapping(source = "userId",target = "user.userId")
    Order toOrder(OrderDtoRequest orderDtoRequest);

    @Mapping(source = "status", target = "status")
    Order toUpdateOrder(OrderUpdateRequest orderUpdateRequest, @MappingTarget Order order);
}
