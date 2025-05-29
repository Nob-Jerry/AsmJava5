package org.asmjava5.service.impl;

import lombok.RequiredArgsConstructor;
import org.asmjava5.data.dto.request.CategoryDtoRequest;
import org.asmjava5.data.dto.response.CategoryDtoResponse;
import org.asmjava5.repository.CategoryRepository;
import org.asmjava5.service.CategoryService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public List<CategoryDtoResponse> getCategories() throws SQLException {
        return List.of();
    }

    @Override
    public CategoryDtoResponse getCategoryById(Long categoryId) throws SQLException {
        return null;
    }

    @Override
    public Boolean deleteCategoryById(Long categoryId) throws SQLException {
        return null;
    }

    @Override
    public Boolean updateCategory(CategoryDtoRequest categoryDtoRequest) throws SQLException {
        return null;
    }

    @Override
    public Boolean addCategory(CategoryDtoRequest categoryDtoRequest) throws SQLException {
        return null;
    }
}
