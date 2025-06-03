package org.asmjava5.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class UserDtoRequest {
    private Long userId;
    private String username;
    private String email;
    private String fullname;
    private String phone;
    private String address;
    private String role;
    private Boolean isActive;
    private String createdAt;
}
