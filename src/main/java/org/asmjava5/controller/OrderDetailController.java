package org.asmjava5.controller;

import lombok.RequiredArgsConstructor;
import org.asmjava5.common.ApiResponse;
import org.asmjava5.data.dto.request.OrderDetailDtoRequest;
import org.asmjava5.data.dto.request.OrderDtoRequest;
import org.asmjava5.data.dto.request.update.OrderDetailUpdateRequest;
import org.asmjava5.data.dto.request.update.OrderUpdateRequest;
import org.asmjava5.service.OrderDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-detail")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .data(orderDetailService.getOrderDetail(id))
                .build());
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .data(orderDetailService.getAllOrderDetails())
                .build());
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveOrder(@RequestBody OrderDetailDtoRequest orderDetailDtoRequest) {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .data(orderDetailService.addOrderDetail(orderDetailDtoRequest))
                .build());
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateOrder(@RequestBody OrderDetailUpdateRequest orderDetailUpdateRequest) {
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .data(orderDetailService.updateOrderDetail(orderDetailUpdateRequest))
                .build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id) {
        orderDetailService.deleteOrderDetail(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .status(200)
                .success(true)
                .message("Order deleted")
                .build());
    }
}
