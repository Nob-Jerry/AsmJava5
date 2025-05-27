package org.asmjava5.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderDtoRequest {
    private Long userId;
    private List<OrderDetailDtoRequest> orderDetails;
}
