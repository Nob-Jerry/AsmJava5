package org.asmjava5.Authenticate.controller;

import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.asmjava5.Authenticate.data.dto.request.*;
import org.asmjava5.Authenticate.data.dto.response.GoogleLoginResponse;
import org.asmjava5.Authenticate.data.dto.response.IntrospectDtoResponse;
import org.asmjava5.Authenticate.data.dto.response.LoginDtoResponse;
import org.asmjava5.Authenticate.service.LoginService;
import org.asmjava5.Authenticate.service.RegisterService;
import org.asmjava5.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class LoginController {

    private final RegisterService registerService;
    private final LoginService loginService;

    @PostMapping("/login")
    public LoginDtoResponse login(@RequestBody LoginDtoRequest request) throws JOSEException, ParseException {
        return loginService.authenticate(request);
    }

    @PostMapping("/google-login")
    public GoogleLoginResponse googleLogin(@RequestBody GoogleLoginRequest request) throws JOSEException, ParseException, GeneralSecurityException, IOException {
        return loginService.googleLogin(request);
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectDtoResponse> introspect(@RequestBody IntrospectDtoRequest request) throws JOSEException, ParseException {
        return ApiResponse.<IntrospectDtoResponse>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .data(loginService.introspect(request))
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutDtoRequest accessToken) throws ParseException, JOSEException {
        return ApiResponse.<Void>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .data(loginService.logout(accessToken))
                .build();
    }

    @PostMapping("/update-password")
    ApiResponse<Void> update(@RequestBody PasswordUpdateRequest request) {
        registerService.updatePassword(request);
        return ApiResponse.<Void>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .message("Password updated successfully")
                .build();
    }

    @PostMapping("/register")
    ApiResponse<Void> register(@RequestBody RegisterDtoRequest request) throws JOSEException {
        registerService.register(request);
        return ApiResponse.<Void>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .message("Register successfully")
                .build();
    }

}
