package org.asmjava5.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetailDtoRequest {
    private Long productId;
    private Integer quantity;
}
