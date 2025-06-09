package org.asmjava5.service.impl;

import lombok.RequiredArgsConstructor;
import org.asmjava5.convert.OrderMapstruct;
import org.asmjava5.data.dto.request.OrderDetailDtoRequest;
import org.asmjava5.data.dto.request.OrderDtoRequest;
import org.asmjava5.data.dto.request.update.OrderUpdateRequest;
import org.asmjava5.data.dto.response.OrderDtoResponse;
import org.asmjava5.data.entity.Order;
import org.asmjava5.data.entity.OrderDetail;
import org.asmjava5.data.entity.Product;
import org.asmjava5.enums.ErrorCode;
import org.asmjava5.exception.AppException;
import org.asmjava5.repository.OrderDetailRepository;
import org.asmjava5.repository.OrderRepository;
import org.asmjava5.repository.ProductRepository;
import org.asmjava5.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final OrderMapstruct orderMapstruct;

    @Override
    @Transactional
    public OrderDtoResponse getOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.T_EMPTY));
        return orderMapstruct.toOrderDtoResponse(order);
    }

    @Override
    @Transactional
    public List<OrderDtoResponse> getOrderByUser(Long userId) {
        List<Order> orders = orderRepository.findByUser_UserId(userId);
        return orderMapstruct.toOrderDtoResponseList(orders);
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
        try {
            Order order = orderMapstruct.toOrder(orderDtoRequest);
            order.setOrderDate(new Date());
            orderRepository.save(order);
            for (OrderDetailDtoRequest request : orderDtoRequest.getOrderDetails()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order);
                Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new AppException(ErrorCode.T_EMPTY));
                orderDetail.setProduct(product);
                orderDetail.setQuantity(request.getQuantity());
                orderDetail.setPrice(request.getPrice());
                orderDetailRepository.save(orderDetail);
            }
            return true;
        }catch (AppException e) {
            throw new AppException(ErrorCode.FAIL_TO_SAVE_UPDATE);
        }
    }

    @Override
    public Boolean updateOrder(OrderUpdateRequest orderUpdateRequest) {
        Order check = orderRepository.findById(orderUpdateRequest.getOrderId()).orElseThrow(()-> new AppException(ErrorCode.FAIL_TO_SAVE_UPDATE));
        Order order = orderMapstruct.toUpdateOrder(orderUpdateRequest, check);
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
