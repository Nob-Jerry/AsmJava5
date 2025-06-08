package org.asmjava5.Authenticate.data.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordRequest {
    private String token;
    private String password;
}
