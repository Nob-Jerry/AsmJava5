package org.asmjava5.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDetailDtoResponse {
    private Long orderDetailId;
    private String productImage;
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;
}
