package org.asmjava5.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetailDtoResponse {
    private Long orderDetailId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;
}
