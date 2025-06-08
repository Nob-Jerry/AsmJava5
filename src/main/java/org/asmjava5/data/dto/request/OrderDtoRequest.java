package org.asmjava5.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderDtoRequest {
    private Long userId;
    private Date orderDate;
    private String orderStatus;
    private Double totalAmount;
    private List<OrderDetailDtoRequest> orderDetails;
}
