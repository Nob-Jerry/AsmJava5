package org.asmjava5.service;

import org.asmjava5.data.dto.request.OrderDetailDtoRequest;
import org.asmjava5.data.dto.request.update.OrderDetailUpdateRequest;
import org.asmjava5.data.dto.response.OrderDetailDtoResponse;

import java.util.List;

public interface OrderDetailService {
    OrderDetailDtoResponse getOrderDetail(Long orderId);
    List<OrderDetailDtoResponse> getAllOrderDetails();
    Boolean deleteOrderDetail(Long orderId);
    Boolean addOrderDetail(OrderDetailDtoRequest orderDetailDtoRequest);
    Boolean updateOrderDetail(OrderDetailUpdateRequest orderDetailUpdateRequest);
}
