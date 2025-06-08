package org.asmjava5.data.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.asmjava5.data.dto.request.OrderDetailDtoRequest;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderUpdateRequest {
    private Long orderId;
    private Long userId;
    private Date orderDate;
    private String orderStatus;
    private Double totalAmount;
    private List<OrderDetailDtoRequest> orderDetails;
}
