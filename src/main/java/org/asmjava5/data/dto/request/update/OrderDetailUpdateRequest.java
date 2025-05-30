package org.asmjava5.data.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetailUpdateRequest {
    private Long orderDetailId;
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private Double price;
}
