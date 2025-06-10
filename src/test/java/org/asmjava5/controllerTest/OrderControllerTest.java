package org.asmjava5.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.asmjava5.controller.OrderController;
import org.asmjava5.convert.OrderMapstruct;
import org.asmjava5.data.dto.request.OrderDtoRequest;
import org.asmjava5.data.dto.request.update.OrderUpdateRequest;
import org.asmjava5.data.entity.Order;
import org.asmjava5.service.OrderService;
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
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private OrderMapstruct orderMapstruct;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetOrderByIdSuccess() throws Exception {
        // Arrange
        Order order = new Order();
        order.setOrderId(1L);
        order.setOrderDate(new Date());
        order.setStatus("PENDING");

        when(orderService.getOrder(1L)).thenReturn(orderMapstruct.toOrderDtoResponse(order));

        // Act & Assert
        mockMvc.perform(get("/api/v1/order/order")
                .param("orderId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.orderId").value(1))
                .andExpect(jsonPath("$.data.status").value("PENDING"));
    }

    @Test
    void testGetOrderByIdNotFound() throws Exception {
        // Arrange
        when(orderService.getOrder(999L)).thenReturn(null);

        // Act & Assert
        mockMvc.perform(get("/api/v1/order/order")
                .param("orderId", "999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testGetOrderByUserSuccess() throws Exception {
        // Arrange
        Order order1 = new Order();
        order1.setOrderId(1L);
        order1.setStatus("PENDING");

        Order order2 = new Order();
        order2.setOrderId(2L);
        order2.setStatus("COMPLETED");

        List<Order> orders = Arrays.asList(order1, order2);

        when(orderService.getOrderByUser(1L)).thenReturn(orderMapstruct.toOrderDtoResponseList(orders));

        // Act & Assert
        mockMvc.perform(get("/api/v1/order/user")
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].orderId").value(1))
                .andExpect(jsonPath("$.data[0].status").value("PENDING"))
                .andExpect(jsonPath("$.data[1].orderId").value(2))
                .andExpect(jsonPath("$.data[1].status").value("COMPLETED"));
    }

    @Test
    void testGetAllOrdersSuccess() throws Exception {
        // Arrange
        Order order1 = new Order();
        order1.setOrderId(1L);
        order1.setStatus("PENDING");

        Order order2 = new Order();
        order2.setOrderId(2L);
        order2.setStatus("COMPLETED");

        Order order3 = new Order();
        order3.setOrderId(3L);
        order3.setStatus("CANCELLED");

        List<Order> orders = Arrays.asList(order1, order2, order3);

        when(orderService.getOrders()).thenReturn(orderMapstruct.toOrderDtoResponseList(orders));

        // Act & Assert
        mockMvc.perform(get("/api/v1/order/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].orderId").value(1))
                .andExpect(jsonPath("$.data[1].orderId").value(2))
                .andExpect(jsonPath("$.data[2].orderId").value(3));
    }

    @Test
    void testSaveOrderSuccess() throws Exception {
        // Arrange
        OrderDtoRequest orderRequest = new OrderDtoRequest();
        orderRequest.setUserId(1L);
        orderRequest.setStatus("PENDING");

        Order savedOrder = new Order();
        savedOrder.setOrderId(1L);
        savedOrder.setStatus("PENDING");

        when(orderService.addOrder(any(OrderDtoRequest.class))).thenReturn(true);

        // Act & Assert
        mockMvc.perform(post("/api/v1/order/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.orderId").value(1))
                .andExpect(jsonPath("$.data.status").value("PENDING"));
    }

    @Test
    void testUpdateOrderStatusSuccess() throws Exception {
        // Arrange
        OrderUpdateRequest updateRequest = new OrderUpdateRequest();
        updateRequest.setOrderId(1L);
        updateRequest.setStatus("COMPLETED");

        Order updatedOrder = new Order();
        updatedOrder.setOrderId(1L);
        updatedOrder.setStatus("COMPLETED");

        when(orderService.updateOrder(any(OrderUpdateRequest.class))).thenReturn(true);

        // Act & Assert
        mockMvc.perform(post("/api/v1/order/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.orderId").value(1))
                .andExpect(jsonPath("$.data.status").value("COMPLETED"));
    }

    @Test
    void testUpdateOrderStatusFromPendingToCompleted() throws Exception {
        // Arrange
        OrderUpdateRequest updateRequest = new OrderUpdateRequest();
        updateRequest.setOrderId(1L);
        updateRequest.setStatus("COMPLETED");

        Order updatedOrder = new Order();
        updatedOrder.setOrderId(1L);
        updatedOrder.setStatus("COMPLETED");

        when(orderService.updateOrder(any(OrderUpdateRequest.class))).thenReturn(true);

        // Act & Assert
        mockMvc.perform(post("/api/v1/order/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.status").value("COMPLETED"));
    }

    @Test
    void testUpdateOrderStatusFromPendingToCancelled() throws Exception {
        // Arrange
        OrderUpdateRequest updateRequest = new OrderUpdateRequest();
        updateRequest.setOrderId(1L);
        updateRequest.setStatus("CANCELLED");

        Order updatedOrder = new Order();
        updatedOrder.setOrderId(1L);
        updatedOrder.setStatus("CANCELLED");

        when(orderService.updateOrder(any(OrderUpdateRequest.class))).thenReturn(true);

        // Act & Assert
        mockMvc.perform(post("/api/v1/order/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.status").value("CANCELLED"));
    }

    @Test
    void testUpdateOrderStatusFromCompletedToShipped() throws Exception {
        // Arrange
        OrderUpdateRequest updateRequest = new OrderUpdateRequest();
        updateRequest.setOrderId(1L);
        updateRequest.setStatus("SHIPPED");

        Order updatedOrder = new Order();
        updatedOrder.setOrderId(1L);
        updatedOrder.setStatus("SHIPPED");

        when(orderService.updateOrder(any(OrderUpdateRequest.class))).thenReturn(true);

        // Act & Assert
        mockMvc.perform(post("/api/v1/order/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.status").value("SHIPPED"));
    }

    @Test
    void testDeleteOrderSuccess() throws Exception {
        // Arrange
        when(orderService.deleteOrder(1L)).thenReturn(true);

        // Act & Assert
        mockMvc.perform(delete("/api/v1/order/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Order deleted"));
    }

    @Test
    void testUpdateOrderWithInvalidStatus() throws Exception {
        // Arrange
        OrderUpdateRequest updateRequest = new OrderUpdateRequest();
        updateRequest.setOrderId(1L);
        updateRequest.setStatus("INVALID_STATUS");

        when(orderService.updateOrder(any(OrderUpdateRequest.class)))
                .thenThrow(new RuntimeException("Invalid status"));

        // Act & Assert
        mockMvc.perform(post("/api/v1/order/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testGetOrderByUserEmpty() throws Exception {
        // Arrange
        when(orderService.getOrderByUser(1L)).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/v1/order/user")
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void testSaveOrderWithException() throws Exception {
        // Arrange
        OrderDtoRequest orderRequest = new OrderDtoRequest();
        orderRequest.setUserId(1L);

        when(orderService.addOrder(any(OrderDtoRequest.class)))
                .thenThrow(new RuntimeException("Invalid order data"));

        // Act & Assert
        mockMvc.perform(post("/api/v1/order/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderRequest)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testGetAllOrdersEmpty() throws Exception {
        // Arrange
        when(orderService.getOrders()).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/v1/order/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty());
    }
} 