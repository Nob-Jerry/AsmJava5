package org.asmjava5.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDtoResponse {
    private Long cartItemId;
    private Integer quantity;
    private Long productId;
    private String productName;
    private Double productPrice;
    private String productImage;
}
