package org.asmjava5.Authenticate.service;

import com.nimbusds.jose.JOSEException;
import org.asmjava5.Authenticate.data.dto.request.ForgotPasswordRequest;
import org.asmjava5.data.entity.User;

public interface VerifyEmailService {
    Boolean verifyEmailByToken(String token);
    void resetPasswordByToken(ForgotPasswordRequest forgotPasswordRequest);
    String generateVerifyToken(User user) throws JOSEException;
}
