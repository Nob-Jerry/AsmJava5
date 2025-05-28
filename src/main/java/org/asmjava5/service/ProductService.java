package org.asmjava5.service;

import org.asmjava5.data.dto.request.ProductDtoRequest;
import org.asmjava5.data.dto.response.ProductDtoResponse;
import org.asmjava5.exception.AppException;

import java.util.List;

public interface ProductService {
    List<ProductDtoResponse> getAllProducts() throws AppException;
    ProductDtoResponse getProductById(long id);
    Boolean saveProduct(ProductDtoRequest productDtoRequest);
    Boolean updateProduct(ProductDtoRequest productDtoRequest);
    Boolean deleteProduct(long id);
}
