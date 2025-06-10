package org.asmjava5.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.asmjava5.controller.ProductController;
import org.asmjava5.convert.ProductMapstruct;
import org.asmjava5.data.dto.request.ProductDtoRequest;
import org.asmjava5.data.dto.request.update.ProductUpdateRequest;
import org.asmjava5.data.dto.response.ProductDtoResponse;
import org.asmjava5.data.entity.Product;
import org.asmjava5.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductMapstruct productMapstruct;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllProductsSuccess() throws Exception {
        Product product1 = new Product();
        product1.setProductId(1L);
        product1.setProductName("Test Product 1");
        product1.setProductPrice(100.0);

        Product product2 = new Product();
        product2.setProductId(2L);
        product2.setProductName("Test Product 2");
        product2.setProductPrice(200.0);

        List<ProductDtoResponse> products = productMapstruct.toProductDtoResponseList(Arrays.asList(product1, product2));

        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/api/v1/product/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].productId").value(1))
                .andExpect(jsonPath("$.data[0].productName").value("Test Product 1"))
                .andExpect(jsonPath("$.data[1].productId").value(2))
                .andExpect(jsonPath("$.data[1].productName").value("Test Product 2"));
    }

    @Test
    void testGetAllProductsEmpty() throws Exception {
        when(productService.getAllProducts()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/v1/product/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testGetProductByIdSuccess() throws Exception {
        // Arrange
        ProductDtoResponse product = new ProductDtoResponse();
        product.setProductId(1L);
        product.setProductName("Test Product");
        product.setProductPrice(100.0);

        when(productService.getProductById(1L)).thenReturn(product);

        // Act & Assert
        mockMvc.perform(get("/api/v1/product/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.productId").value(1))
                .andExpect(jsonPath("$.data.productName").value("Test Product"))
                .andExpect(jsonPath("$.data.price").value(100.0));
    }

    @Test
    void testGetProductByIdNotFound() throws Exception {
        // Arrange
        when(productService.getProductById(999L)).thenReturn(null);

        // Act & Assert
        mockMvc.perform(get("/api/v1/product/999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testSaveProductSuccess() throws Exception {
        ProductDtoRequest productRequest = new ProductDtoRequest();
        productRequest.setProductName("New Product");
        productRequest.setProductPrice(150.0);

        Product savedProduct = new Product();
        savedProduct.setProductId(1L);
        savedProduct.setProductName("New Product");
        savedProduct.setProductPrice(150.0);

        when(productService.saveProduct(any(ProductDtoRequest.class))).thenReturn(true);

        mockMvc.perform(post("/api/v1/product/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.productId").value(1))
                .andExpect(jsonPath("$.data.productName").value("New Product"));
    }

    @Test
    void testUpdateProductSuccess() throws Exception {
        ProductUpdateRequest updateRequest = new ProductUpdateRequest();
        updateRequest.setProductId(1L);
        updateRequest.setProductName("Updated Product");
        updateRequest.setProductPrice(200.0);

        ProductUpdateRequest updatedProduct = new ProductUpdateRequest();
        updatedProduct.setProductId(1L);
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductPrice(200.0);

        when(productService.updateProduct(any(ProductUpdateRequest.class))).thenReturn(true);

        mockMvc.perform(put("/api/v1/product/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.productName").value("Updated Product"));
    }

    @Test
    void testDeleteProductSuccess() throws Exception {
        when(productService.deleteProduct(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/v1/product/delete")
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Product deleted"));
    }

    @Test
    void testGetAllProductsWithException() throws Exception {
        when(productService.getAllProducts()).thenThrow(new RuntimeException("Database error"));

        mockMvc.perform(get("/api/v1/product/all"))
                .andExpect(status().isInternalServerError());
    }
} 