package org.asmjava5.Authenticate.data.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginDtoResponse {
    private String token;
}
