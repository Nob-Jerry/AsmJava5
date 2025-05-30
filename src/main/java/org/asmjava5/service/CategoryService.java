package org.asmjava5.service;

import org.asmjava5.data.dto.request.CategoryDtoRequest;
import org.asmjava5.data.dto.request.update.CategoryUpdateRequest;
import org.asmjava5.data.dto.response.CategoryDtoResponse;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {
    List<CategoryDtoResponse> getCategories();
    CategoryDtoResponse getCategoryById(Long categoryId) ;
    Boolean deleteCategoryById(Long categoryId);
    Boolean updateCategory(CategoryUpdateRequest categoryUpdateRequest);
    Boolean addCategory(CategoryDtoRequest categoryDtoRequest);
}
