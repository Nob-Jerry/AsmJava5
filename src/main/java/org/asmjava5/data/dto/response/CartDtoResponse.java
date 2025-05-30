package org.asmjava5.data.dto.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CartDtoResponse {
    private Long cartId;
    private Long userId;
    private String username;
    private Date createAt;
    private List<CartItemDtoResponse> cartItems;
}
