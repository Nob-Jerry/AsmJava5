package org.asmjava5.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class UserDtoRequest {
    private String username;
    private String password;
    private String email;
    private String fullname;
    private String phone;
    private String address;
}
