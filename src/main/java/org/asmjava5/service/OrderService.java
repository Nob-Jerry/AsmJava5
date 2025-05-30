package org.asmjava5.service;

import org.asmjava5.data.dto.request.OrderDtoRequest;
import org.asmjava5.data.dto.request.update.OrderUpdateRequest;
import org.asmjava5.data.dto.response.OrderDtoResponse;

import java.util.List;

public interface OrderService {
    OrderDtoResponse getOrder(Long id);
    List<OrderDtoResponse> getOrders();
    Boolean addOrder(OrderDtoRequest orderDtoRequest);
    Boolean updateOrder(OrderUpdateRequest orderUpdateRequest);
    Boolean deleteOrder(Long id);
}
