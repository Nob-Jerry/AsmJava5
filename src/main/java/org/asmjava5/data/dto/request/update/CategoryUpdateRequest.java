package org.asmjava5.data.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryUpdateRequest {
    private Long categoryId;
    private String categoryName;
}
