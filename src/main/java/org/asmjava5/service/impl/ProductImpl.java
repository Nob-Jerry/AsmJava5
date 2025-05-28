package org.asmjava5.service.impl;

import lombok.RequiredArgsConstructor;
import org.asmjava5.convert.ProductMapstruct;
import org.asmjava5.data.dto.request.ProductDtoRequest;
import org.asmjava5.data.dto.response.ProductDtoResponse;
import org.asmjava5.data.entity.Product;
import org.asmjava5.enums.ErrorCode;
import org.asmjava5.exception.AppException;
import org.asmjava5.repository.ProductRepository;
import org.asmjava5.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapstruct productMapstruct;


    @Override
    public List<ProductDtoResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new AppException(ErrorCode.BAD_SQL);
        }
        return productMapstruct.toProductDtoResponseList(products);
    }

    @Override
    public ProductDtoResponse getProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BAD_SQL));
        return productMapstruct.toProductDtoResponse(product);
    }

    @Override
    public Boolean saveProduct(ProductDtoRequest productDtoRequest) {
        try {
            Product product = productMapstruct.toProduct(productDtoRequest);
            productRepository.save(product);
            return true;
        } catch (RuntimeException e) {
            throw new AppException(ErrorCode.BAD_SQL);
        }
    }

    @Override
    public Boolean updateProduct(ProductDtoRequest productDtoRequest) {
        try{
            Product product = productMapstruct.toProduct(productDtoRequest);
            productRepository.save(product);
            return true;
        } catch (RuntimeException e) {
            throw new AppException(ErrorCode.BAD_SQL);
        }
    }

    @Override
    public Boolean deleteProduct(long id) {
        try {
            var product = productRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.BAD_SQL));
            return true;
        } catch (RuntimeException e) {
            throw new AppException(ErrorCode.BAD_SQL);
        }
    }
}
