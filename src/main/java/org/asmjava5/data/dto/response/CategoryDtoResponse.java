package org.asmjava5.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryDtoResponse {
    private Long categoryId;
    private String categoryName;
    private List<ProductDtoResponse> products;
}
