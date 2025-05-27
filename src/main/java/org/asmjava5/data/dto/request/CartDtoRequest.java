package org.asmjava5.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CartDtoRequest {
    private Long userId;
    private List<CartItemDtoRequest> cartItems;
}
