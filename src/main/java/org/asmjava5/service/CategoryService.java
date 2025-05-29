package org.asmjava5.service;

import org.asmjava5.data.dto.request.CategoryDtoRequest;
import org.asmjava5.data.dto.response.CategoryDtoResponse;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {
    List<CategoryDtoResponse> getCategories() throws SQLException;
    CategoryDtoResponse getCategoryById(Long categoryId) throws SQLException;
    Boolean deleteCategoryById(Long categoryId) throws SQLException;
    Boolean updateCategory(CategoryDtoRequest categoryDtoRequest) throws SQLException;
    Boolean addCategory(CategoryDtoRequest categoryDtoRequest) throws SQLException;
}
