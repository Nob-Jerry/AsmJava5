package org.asmjava5.data.dto.request.update;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemUpdateRequest {
    private Long cartItemId;
    private Long cartId;
    private Long productId;
    private Integer quantity;
}
