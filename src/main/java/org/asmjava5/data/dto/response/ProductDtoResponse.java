package org.asmjava5.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
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
    private Double discountPercent;
    private Double rating;
}
