package org.asmjava5.data.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserUpdateRequest {
    private String username;
    private String email;
    private String fullname;
    private String phone;
    private String address;
    private String role;
    private Boolean isActive;
}
