package org.asmjava5.Authenticate.service;

import com.nimbusds.jose.JOSEException;
import org.asmjava5.Authenticate.data.dto.request.PasswordUpdateRequest;
import org.asmjava5.Authenticate.data.dto.request.RegisterDtoRequest;

public interface RegisterService {
    Boolean register(RegisterDtoRequest request) throws JOSEException;
    Boolean updatePassword(PasswordUpdateRequest request);
}
