package org.asmjava5.data.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartItemUpdateRequest {
    private Long cartItemId;
    private Long cartId;
    private Long productId;
    private Integer quantity;
}
