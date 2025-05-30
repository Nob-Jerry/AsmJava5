package org.asmjava5.controller;

import lombok.RequiredArgsConstructor;
import org.asmjava5.common.ApiResponse;
import org.asmjava5.data.dto.request.OrderDtoRequest;
import org.asmjava5.data.dto.request.update.OrderUpdateRequest;
import org.asmjava5.data.dto.response.OrderDtoResponse;
import org.asmjava5.service.OrderService;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.builder()
                        .status(200)
                        .success(true)
                        .data(orderService.getOrder(id))
                .build());
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(ApiResponse.builder()
                        .status(200)
                        .success(true)
                        .data(orderService.getOrders())
                .build());
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveOrder(@RequestBody OrderDtoRequest orderDtoRequest) {
        return ResponseEntity.ok(ApiResponse.builder()
                        .status(200)
                        .success(true)
                        .data(orderService.addOrder(orderDtoRequest))
                .build());
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateOrder(@RequestBody OrderUpdateRequest orderUpdateRequest) {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .data(orderService.updateOrder(orderUpdateRequest))
                .build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok(ApiResponse.builder()
                        .status(200)
                .success(true)
                        .message("Order deleted")
                .build());
    }
}
