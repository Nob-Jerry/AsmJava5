package org.asmjava5.service;

import org.asmjava5.data.dto.request.ProductDtoRequest;
import org.asmjava5.data.dto.request.update.ProductUpdateRequest;
import org.asmjava5.data.dto.response.ProductDtoResponse;
import org.asmjava5.exception.AppException;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List<ProductDtoResponse> getAllProducts();
    ProductDtoResponse getProductById(Long id);
    Boolean saveProduct(ProductDtoRequest productDtoRequest);
    Boolean updateProduct(ProductUpdateRequest productUpdateRequest);
    Boolean deleteProduct(Long id);
}
