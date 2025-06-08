package org.asmjava5.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CategoryDtoResponse {
    private Long categoryId;
    private String categoryName;
    private String description;
    private List<ProductDtoResponse> products;
}
