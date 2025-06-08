package org.asmjava5.service.impl;

import lombok.RequiredArgsConstructor;
import org.asmjava5.convert.CategoryMapstruct;
import org.asmjava5.data.dto.request.CategoryDtoRequest;
import org.asmjava5.data.dto.request.update.CategoryUpdateRequest;
import org.asmjava5.data.dto.response.CategoryDtoResponse;
import org.asmjava5.data.entity.Category;
import org.asmjava5.enums.ErrorCode;
import org.asmjava5.exception.AppException;
import org.asmjava5.repository.CategoryRepository;
import org.asmjava5.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapstruct categoryMapstruct;

    @Override
    @Transactional
    public List<CategoryDtoResponse> getCategories(){
        List<Category> categories = categoryRepository.findAll();
        if (!categories.isEmpty()) {
            return categoryMapstruct.toCategoryDtoResponseList(categories);
        }else {
            throw new AppException(ErrorCode.LIST_EMPTY);
        }
    }

    @Override
    @Transactional
    public CategoryDtoResponse getCategoryById(Long categoryId){
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new AppException(ErrorCode.T_EMPTY));
        return categoryMapstruct.toCategoryDtoResponse(category);
    }

    @Override
    public Boolean deleteCategoryById(Long categoryId){
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new AppException(ErrorCode.T_EMPTY));
        categoryRepository.delete(category);
        return true;
    }

    @Override
    public Boolean updateCategory(CategoryUpdateRequest categoryUpdateRequest){
        Category category = categoryMapstruct.toUpdateCategory(categoryUpdateRequest);
        if (categoryRepository.existsById(category.getCategoryId())){
            categoryRepository.save(category);
            return true;
        }else {
            throw new AppException(ErrorCode.FAIL_TO_SAVE_UPDATE);
        }
    }

    @Override
    public Boolean addCategory(CategoryDtoRequest categoryDtoRequest){
        Category category = categoryMapstruct.toCategory(categoryDtoRequest);
        if (categoryRepository.existsById(category.getCategoryId())){
            throw new AppException(ErrorCode.FAIL_TO_SAVE_UPDATE);
        }else {
            categoryRepository.save(category);
            return true;
        }
    }
}
