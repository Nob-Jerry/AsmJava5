package org.asmjava5.Authenticate.service;

import com.nimbusds.jose.JOSEException;
import org.asmjava5.data.entity.User;

public interface VerifyEmailService {
    boolean verifyEmailByToken(String token);
    String generateVerifyToken(User user) throws JOSEException;
}
