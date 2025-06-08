package org.asmjava5.service.impl;

import lombok.RequiredArgsConstructor;
import org.asmjava5.convert.OrderMapstruct;
import org.asmjava5.data.dto.request.OrderDtoRequest;
import org.asmjava5.data.dto.request.update.OrderUpdateRequest;
import org.asmjava5.data.dto.response.OrderDtoResponse;
import org.asmjava5.data.entity.Order;
import org.asmjava5.enums.ErrorCode;
import org.asmjava5.exception.AppException;
import org.asmjava5.repository.OrderRepository;
import org.asmjava5.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapstruct orderMapstruct;

    @Override
    public OrderDtoResponse getOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.T_EMPTY));
        return orderMapstruct.toOrderDtoResponse(order);
    }

    @Override
    @Transactional
    public List<OrderDtoResponse> getOrders() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) throw new AppException(ErrorCode.LIST_EMPTY);
        return orderMapstruct.toOrderDtoResponseList(orders);
    }

    @Override
    public Boolean addOrder(OrderDtoRequest orderDtoRequest) {
        Order check = orderRepository.findById(orderDtoRequest.getUserId()).orElse(null);
        if (check == null) {
            Order order = orderMapstruct.toOrder(orderDtoRequest);
            order.setOrderDate(new Date());
            orderRepository.save(order);
            return true;
        } else {
            throw new AppException(ErrorCode.FAIL_TO_SAVE_UPDATE);
        }
    }

    @Override
    public Boolean updateOrder(OrderUpdateRequest orderUpdateRequest) {
        Order order = orderRepository.findById(orderUpdateRequest.getOrderId()).orElseThrow(()-> new AppException(ErrorCode.FAIL_TO_SAVE_UPDATE));
        orderRepository.save(order);
        return true;
    }

    @Override
    public Boolean deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.FAIL_DELETE));
        orderRepository.delete(order);
        return true;
    }
}
