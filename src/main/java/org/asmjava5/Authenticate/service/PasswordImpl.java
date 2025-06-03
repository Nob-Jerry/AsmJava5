package org.asmjava5.Authenticate.service;

import lombok.RequiredArgsConstructor;
import org.asmjava5.Authenticate.data.dto.request.PasswordUpdateRequest;
import org.asmjava5.data.entity.User;
import org.asmjava5.enums.ErrorCode;
import org.asmjava5.exception.AppException;
import org.asmjava5.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordImpl implements PasswordService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
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

