package org.asmjava5.Authenticate.service.impl;

import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.asmjava5.Authenticate.data.dto.request.ForgotPasswordRequest;
import org.asmjava5.Authenticate.service.ForgotPassword;
import org.asmjava5.Authenticate.service.VerifyEmailService;
import org.asmjava5.common.SendVerificationMail;
import org.asmjava5.data.entity.User;
import org.asmjava5.enums.ErrorCode;
import org.asmjava5.exception.AppException;
import org.asmjava5.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForgotPasswordImpl implements ForgotPassword {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerifyEmailService verifyEmailService;
    private final SendVerificationMail sendMail;

    @Override
    public Void forgotPassword(String email) throws JOSEException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new AppException(ErrorCode.USER_EMPTY);
        }
        String token = verifyEmailService.generateVerifyToken(user);
        sendMail.sendEmailResetPassword(email, token);
        return null;
    }

    @Override
    public Void resetPassword(ForgotPasswordRequest forgotPasswordRequest) {
        verifyEmailService.resetPasswordByToken(forgotPasswordRequest);
        return null;
    }
}
