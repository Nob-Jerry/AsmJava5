package org.asmjava5.Authenticate.data.dto.request;

import lombok.*;

@Getter
@Setter
public class LoginDtoRequest {
    private String username;
    private String password;
}
