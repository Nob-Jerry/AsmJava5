package org.asmjava5.Authenticate.controller;

import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.asmjava5.Authenticate.data.dto.request.ForgotPasswordRequest;
import org.asmjava5.Authenticate.data.dto.request.LogoutDtoRequest;
import org.asmjava5.Authenticate.service.ForgotPassword;
import org.asmjava5.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class ForgotPasswordController {
    private final ForgotPassword forgotPassword;

    @PostMapping("/forgot-password")
    ApiResponse<Void> forgotPassword(@RequestParam("email") String email) throws JOSEException {
        return ApiResponse.<Void>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .data(forgotPassword.forgotPassword(email))
                .build();
    }

    @PostMapping("/reset-password")
    ApiResponse<Void> resetPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) throws ParseException, JOSEException {
        return ApiResponse.<Void>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .data(forgotPassword.resetPassword(forgotPasswordRequest))
                .build();
    }

}
