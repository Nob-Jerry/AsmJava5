package org.asmjava5.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ProductDtoResponse {
    private Long productId;
    private String productName;
    private Double productPrice;
    private Integer productQuantity;
    private Long categoryId;
    private String categoryName;
    private String description;
    private String imageUrl;
    private Date createdAt;
    private Boolean isHot;
    private Boolean isDiscount;
    private Boolean isNew;
}
