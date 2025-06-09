package org.asmjava5.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDetailDtoRequest {
    private Long productId;
    private Integer quantity;
    private Double price;
}
