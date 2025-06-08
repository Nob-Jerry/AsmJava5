package org.asmjava5.Authenticate.service.impl;

import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.asmjava5.Authenticate.data.dto.request.PasswordUpdateRequest;
import org.asmjava5.Authenticate.data.dto.request.RegisterDtoRequest;
import org.asmjava5.Authenticate.service.RegisterService;
import org.asmjava5.Authenticate.service.VerifyEmailService;
import org.asmjava5.common.SendMail;
import org.asmjava5.common.SendVerificationMail;
import org.asmjava5.data.entity.User;
import org.asmjava5.enums.ErrorCode;
import org.asmjava5.exception.AppException;
import org.asmjava5.repository.UserRepository;
import org.asmjava5.service.CartService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class RegisterImpl implements RegisterService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerifyEmailService verifyEmailService;
    private final SendVerificationMail sendMail;
    private final CartService cartService;
    @Override
    public Boolean register(RegisterDtoRequest request) throws JOSEException {
        if (userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USERNAME_EXIST);
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXIST);
        }
        if (!request.getPassword().equals(request.getConfirmPassword())){
            throw new AppException(ErrorCode.PASSWORD_NOT_MATCH);
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        user.setIsActive(true);
        user.setIsVerified(false);
        user.setCreatedAt(new Date());
        String activationToken = verifyEmailService.generateVerifyToken(user);
        user.setActivationToken(activationToken);
        userRepository.save(user);
        cartService.createCart(user.getUserId());
        sendMail.sendVerificationEmail(user.getEmail(), activationToken);
        return true;
    }

    @Override
    public Boolean updatePassword(PasswordUpdateRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new AppException(ErrorCode.USER_EMPTY));
        if (user != null) {
            if (!passwordEncoder.matches(request.getOldPass(), user.getPassword())) {
                throw new AppException(ErrorCode.INVALID_OLD_PASSWORD);
            }

            if (!request.getNewPass().equals(request.getConfirmPass())) {
                throw new AppException(ErrorCode.PASSWORD_CONFIRM_MISMATCH);
            }

                user.setPassword(passwordEncoder.encode(request.getNewPass()));
                int row = userRepository.updatePassword(user.getPassword(), request.getUsername());
                return true;
        } else {
            throw new AppException(ErrorCode.FAIL_TO_SAVE_UPDATE);
        }

    }
}

