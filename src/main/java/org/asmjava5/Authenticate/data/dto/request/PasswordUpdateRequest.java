package org.asmjava5.Authenticate.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PasswordUpdateRequest {
    private String username;
    private String oldPass;
    private String newPass;
    private String confirmPass;
}
