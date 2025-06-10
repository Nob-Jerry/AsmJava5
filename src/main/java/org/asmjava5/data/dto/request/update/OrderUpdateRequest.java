package org.asmjava5.data.dto.request.update;

import lombok.*;
import org.asmjava5.data.dto.request.OrderDetailDtoRequest;
import org.asmjava5.data.dto.response.OrderDetailDtoResponse;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateRequest {
    private Long orderId;
    private String status;
}
