package org.asmjava5.data.dto.request;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserDtoRequest {
    private String username;
    private String email;
    private String password;
    private String fullname;
    private String phone;
    private String address;
}
