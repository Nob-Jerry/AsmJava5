package org.asmjava5.service.impl;

import lombok.RequiredArgsConstructor;
import org.asmjava5.convert.OrderDetailMapstruct;
import org.asmjava5.data.dto.request.OrderDetailDtoRequest;
import org.asmjava5.data.dto.request.update.OrderDetailUpdateRequest;
import org.asmjava5.data.dto.response.OrderDetailDtoResponse;
import org.asmjava5.data.entity.OrderDetail;
import org.asmjava5.enums.ErrorCode;
import org.asmjava5.exception.AppException;
import org.asmjava5.repository.OrderDetailRepository;
import org.asmjava5.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapstruct orderDetailMapstruct;
    @Override
    public OrderDetailDtoResponse getOrderDetail(Long orderId) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderId).orElseThrow(()->new AppException(ErrorCode.T_EMPTY));
        return orderDetailMapstruct.toOrderDetailDtoResponse(orderDetail);
    }

    @Override
    public List<OrderDetailDtoResponse> getAllOrderDetails() {
        List<OrderDetail> orderDetailList = orderDetailRepository.findAll();
        if(orderDetailList.isEmpty()) throw new AppException(ErrorCode.LIST_EMPTY);
        return orderDetailMapstruct.toOrderDetailDtoResponseList(orderDetailList);
    }

    @Override
    public Boolean deleteOrderDetail(Long orderId) {
        try {
            orderDetailRepository.deleteById(orderId);
            return true;
        }catch (AppException e) {
            throw new AppException(ErrorCode.FAIL_DELETE);
        }
    }

    @Override
    public Boolean addOrderDetail(OrderDetailDtoRequest orderDetailDtoRequest) {
        try {
            OrderDetail orderDetail = orderDetailMapstruct.toOrderDetail(orderDetailDtoRequest);
            orderDetailRepository.save(orderDetail);
            return true;
        }catch (AppException e) {
            throw new AppException(ErrorCode.FAIL_TO_SAVE_UPDATE);
        }
    }

    @Override
    public Boolean updateOrderDetail(OrderDetailUpdateRequest orderDetailUpdateRequest) {
        OrderDetail orderDetail = orderDetailMapstruct.toUpdateOrderDetail(orderDetailUpdateRequest);
        if (orderDetailRepository.existsById(orderDetail.getOrderDetailId())) {
            orderDetailRepository.save(orderDetail);
            return true;
        }else {
            throw new AppException(ErrorCode.FAIL_TO_SAVE_UPDATE);
        }
    }
}
