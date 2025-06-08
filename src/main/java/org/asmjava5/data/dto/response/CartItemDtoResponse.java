package org.asmjava5.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartItemDtoResponse {
    private Long cartItemId;
    private Integer quantity;
    private Long productId;
    private String productName;
    private Double productPrice;
    private String productImage;
}
