package org.asmjava5.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryDtoRequest {
    private String categoryName;
    private String description;
}
