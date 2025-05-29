package org.asmjava5.convert;

import org.asmjava5.data.dto.request.ProductDtoRequest;
import org.asmjava5.data.dto.response.ProductDtoResponse;
import org.asmjava5.data.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapstruct {
    Product toProduct(ProductDtoRequest productDtoRequest);
    ProductDtoResponse toProductDtoResponse(Product product);
    List<ProductDtoResponse> toProductDtoResponseList(List<Product> products);
}
