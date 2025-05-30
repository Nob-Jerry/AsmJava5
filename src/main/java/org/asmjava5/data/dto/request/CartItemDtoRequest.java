package org.asmjava5.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemDtoRequest {
    private Long cartId;
    private Long productId;
    private Integer quantity;
}
