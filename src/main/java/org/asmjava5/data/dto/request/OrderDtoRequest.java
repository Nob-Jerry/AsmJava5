package org.asmjava5.data.dto.request;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDtoRequest {
    private Long userId;
    private Double totalAmount;
    private String status;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private String paymentMethod;
    private List<OrderDetailDtoRequest> orderDetails;
}
