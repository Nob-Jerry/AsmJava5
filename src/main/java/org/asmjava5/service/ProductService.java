package org.asmjava5.service;

import org.asmjava5.data.dto.request.ProductDtoRequest;
import org.asmjava5.data.dto.response.ProductDtoResponse;
import org.asmjava5.exception.AppException;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List<ProductDtoResponse> getAllProducts() throws SQLException;
    ProductDtoResponse getProductById(Long id) throws SQLException;
    Boolean saveProduct(ProductDtoRequest productDtoRequest) throws SQLException;
    Boolean updateProduct(ProductDtoRequest productDtoRequest) throws SQLException;
    Boolean deleteProduct(Long id) throws SQLException;
}
