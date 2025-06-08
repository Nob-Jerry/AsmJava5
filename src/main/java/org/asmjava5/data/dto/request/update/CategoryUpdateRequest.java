package org.asmjava5.data.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryUpdateRequest {
    private Long categoryId;
    private String categoryName;
    private String description;
}
