package org.asmjava5.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderDtoResponse {
    private Long orderId;
    private Long userId;
    private String username;
    private Date orderDate;
    private String status;
    private Double totalAmount;
    private List<OrderDetailDtoResponse> orderDetails;
}
