package org.asmjava5.Authenticate.service;

import com.nimbusds.jose.JOSEException;
import org.asmjava5.Authenticate.data.dto.request.ForgotPasswordRequest;

public interface ForgotPassword {
    Void forgotPassword(String email) throws JOSEException;
    Void resetPassword(ForgotPasswordRequest forgotPasswordRequest);
}
