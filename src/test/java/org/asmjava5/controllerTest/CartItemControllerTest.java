package org.asmjava5.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.asmjava5.controller.CartItemController;
import org.asmjava5.data.dto.request.update.CartItemUpdateRequest;
import org.asmjava5.data.dto.response.CartItemDtoResponse;
import org.asmjava5.data.entity.CartItem;
import org.asmjava5.service.CartItemService;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CartItemControllerTest {

    @Mock
    private CartItemService cartItemService;

    @InjectMocks
    private CartItemController cartItemController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cartItemController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllCartItemsSuccess() throws Exception {
        // Arrange
        CartItem cartItem1 = new CartItem();
        cartItem1.setCartItemId(1L);
        cartItem1.setQuantity(2);

        CartItem cartItem2 = new CartItem();
        cartItem2.setCartItemId(2L);
        cartItem2.setQuantity(1);

        List<CartItem> cartItems = Arrays.asList(cartItem1, cartItem2);

        CartItemDtoResponse dto = new CartItemDtoResponse();
        dto.setCartItemId(1L);
        dto.setQuantity(2);
        dto.setProductName("Product A");

        List<CartItemDtoResponse> dtoList = List.of(dto);
        when(cartItemService.getCartItemList("testuser")).thenReturn(dtoList);

        // Act & Assert
        mockMvc.perform(get("/api/v1/cart-item/all")
                .param("username", "testuser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].cartItemId").value(1))
                .andExpect(jsonPath("$.data[0].quantity").value(2))
                .andExpect(jsonPath("$.data[1].cartItemId").value(2))
                .andExpect(jsonPath("$.data[1].quantity").value(1));
    }

    @Test
    void testGetAllCartItemsEmpty() throws Exception {
        // Arrange
        when(cartItemService.getCartItemList("testuser")).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/v1/cart-item/all")
                .param("username", "testuser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testUpdateCartItemSuccess() throws Exception {
        // Arrange
        CartItemUpdateRequest updateRequest = new CartItemUpdateRequest();
        updateRequest.setCartItemId(1L);
        updateRequest.setQuantity(3);

        CartItemDtoResponse dto = new CartItemDtoResponse();
        dto.setCartItemId(1L);
        dto.setQuantity(3);

        when(cartItemService.updateCartItem(any(CartItemUpdateRequest.class))).thenReturn(dto);

        // Act & Assert
        mockMvc.perform(post("/api/v1/cart-item/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data.cartItemId").value(1))
                .andExpect(jsonPath("$.data.quantity").value(3));
    }

    @Test
    void testUpdateCartItemWithInvalidData() throws Exception {
        // Arrange
        CartItemUpdateRequest updateRequest = new CartItemUpdateRequest();
        updateRequest.setCartItemId(999L);
        updateRequest.setQuantity(-1);

        when(cartItemService.updateCartItem(any(CartItemUpdateRequest.class)))
                .thenThrow(new RuntimeException("Invalid cart item"));

        // Act & Assert
        mockMvc.perform(post("/api/v1/cart-item/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testDeleteCartItemListSuccess() throws Exception {
        // Arrange
        List<Long> productIdList = Arrays.asList(1L, 2L, 3L);
        
        when(cartItemService.deleteCartItemList(1L, productIdList)).thenReturn(true);

        // Act & Assert
        mockMvc.perform(delete("/api/v1/cart-item/delete-list")
                .param("userId", "1")
                .param("productIdList", "1,2,3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data").value(true));
    }

    @Test
    void testDeleteCartItemListWithEmptyList() throws Exception {
        // Arrange
        when(cartItemService.deleteCartItemList(1L, Arrays.asList())).thenReturn(true);

        // Act & Assert
        mockMvc.perform(delete("/api/v1/cart-item/delete-list")
                .param("userId", "1")
                .param("productIdList", ""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Success"));
    }

    @Test
    void testGetAllCartItemsWithInvalidUsername() throws Exception {
        // Arrange
        when(cartItemService.getCartItemList("")).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/v1/cart-item/all")
                .param("username", ""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void testUpdateCartItemWithZeroQuantity() throws Exception {
        // Arrange
        CartItemUpdateRequest updateRequest = new CartItemUpdateRequest();
        updateRequest.setCartItemId(1L);
        updateRequest.setQuantity(0);

        CartItemDtoResponse dto = new CartItemDtoResponse();
        dto.setCartItemId(1L);
        dto.setQuantity(0);

        when(cartItemService.updateCartItem(any(CartItemUpdateRequest.class))).thenReturn(dto);

        // Act & Assert
        mockMvc.perform(post("/api/v1/cart-item/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.quantity").value(0));
    }

//    @Test
//    void testDeleteCartItemListWithException() throws Exception {
//        // Arrange
//        List<Long> productIdList = Arrays.asList(1L, 2L);
//
//        when(cartItemService.deleteCartItemList(1L, productIdList))
//                .thenThrow(new RuntimeException("Database error"));
//
//        // Act & Assert
//        mockMvc.perform(delete("/api/v1/cart-item/delete-list")
//                .param("userId", "1")
//                .param("productIdList", "1,2"))
//                .andExpect(status().isInternalServerError());
//    }
} 