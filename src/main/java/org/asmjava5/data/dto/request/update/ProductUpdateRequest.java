package org.asmjava5.data.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductUpdateRequest {
    private Long productId;
    private String productName;
    private Double productPrice;
    private Integer productQuantity;
    private Long categoryId;
    private String description;
    private String imageUrl;
    private Boolean isHot;
    private Boolean isDiscount;
    private Boolean isNew;
    private Double discountPercent;
}
