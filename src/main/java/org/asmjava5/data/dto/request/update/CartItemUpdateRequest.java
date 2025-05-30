package org.asmjava5.data.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemUpdateRequest {
    private Long itemId;
    private Long cartId;
    private Long productId;
    private Integer quantity;
}
