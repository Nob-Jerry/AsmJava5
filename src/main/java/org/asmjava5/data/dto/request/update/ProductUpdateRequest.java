package org.asmjava5.data.dto.request.update;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest {
    private Long productId;
    private String productName;
    private Double productPrice;
    private Integer productQuantity;
    private Long categoryId;
    private String description;
    private Date createdAt;
    private String imageUrl;
    private Boolean isHot;
    private Boolean isDiscount;
    private Boolean isNew;
    private Double discountPercent;
}
