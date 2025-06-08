package org.asmjava5.service.impl;

import lombok.RequiredArgsConstructor;
import org.asmjava5.convert.ProductMapstruct;
import org.asmjava5.data.dto.request.ProductDtoRequest;
import org.asmjava5.data.dto.request.update.ProductUpdateRequest;
import org.asmjava5.data.dto.response.ProductDtoResponse;
import org.asmjava5.data.entity.Category;
import org.asmjava5.data.entity.Product;
import org.asmjava5.enums.ErrorCode;
import org.asmjava5.exception.AppException;
import org.asmjava5.repository.CategoryRepository;
import org.asmjava5.repository.ProductRepository;
import org.asmjava5.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapstruct productMapstruct;
    private final CategoryRepository categoryRepository;


    @Override
    public List<ProductDtoResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new AppException(ErrorCode.FAIL_GET_LIST);
        }
        return productMapstruct.toProductDtoResponseList(products);
    }

    @Override
    @Transactional
    public ProductDtoResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.FAIL_GET_ONE));
        return productMapstruct.toProductDtoResponse(product);
    }

    @Override
    @Transactional
    public Boolean saveProduct(ProductDtoRequest productDtoRequest) {
            Product product = productMapstruct.toProduct(productDtoRequest);
            try {
                productRepository.save(product);
                return true;
            }catch (AppException e) {
                throw new AppException(ErrorCode.FAIL_TO_SAVE_UPDATE);
            }
    }

    @Override
    @Transactional
    public Boolean updateProduct(ProductUpdateRequest productUpdateRequest) {
        Product product = productMapstruct.toUpdateProduct(productUpdateRequest);
        Category category = categoryRepository.findById(productUpdateRequest.getCategoryId()).orElseThrow(() -> new AppException(ErrorCode.T_EMPTY));
        if (productRepository.existsById(product.getProductId())) {
            product.setCategory(category);
            productRepository.save(product);
            return true;
        }else {
            throw new AppException(ErrorCode.FAIL_TO_SAVE_UPDATE);
        }
    }

    @Override
    @Transactional
    public Boolean deleteProduct(Long id) {
        var product = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.T_EMPTY));
        productRepository.delete(product);
        return true;
    }
}
