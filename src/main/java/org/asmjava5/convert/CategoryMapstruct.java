package org.asmjava5.convert;

import org.asmjava5.data.dto.request.CategoryDtoRequest;
import org.asmjava5.data.dto.request.update.CategoryUpdateRequest;
import org.asmjava5.data.dto.response.CategoryDtoResponse;
import org.asmjava5.data.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapstruct {
    CategoryDtoResponse toCategoryDtoResponse(Category category);
    List<CategoryDtoResponse> toCategoryDtoResponseList(List<Category> categoryList);
    Category toCategory(CategoryDtoRequest categoryDtoRequest);
    Category toUpdateCategory(CategoryUpdateRequest categoryUpdateRequest);
}
