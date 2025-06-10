package org.asmjava5.Authenticate.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDtoRequest {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
}
