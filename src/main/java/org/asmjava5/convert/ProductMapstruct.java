package org.asmjava5.convert;

import org.asmjava5.data.dto.request.ProductDtoRequest;
import org.asmjava5.data.dto.request.update.ProductUpdateRequest;
import org.asmjava5.data.dto.response.ProductDtoResponse;
import org.asmjava5.data.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapstruct {
    Product toProduct(ProductDtoRequest productDtoRequest);

    Product toUpdateProduct(ProductUpdateRequest productUpdateRequest);

    @Mapping(source = "category.categoryId", target = "categoryId")
    @Mapping(source = "category.categoryName", target = "categoryName")
    ProductDtoResponse toProductDtoResponse(Product product);

    List<ProductDtoResponse> toProductDtoResponseList(List<Product> products);
}
