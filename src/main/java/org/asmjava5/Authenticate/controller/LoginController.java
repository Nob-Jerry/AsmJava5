package org.asmjava5.Authenticate.controller;

import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.asmjava5.Authenticate.data.dto.request.IntrospectDtoRequest;
import org.asmjava5.Authenticate.data.dto.request.LoginDtoRequest;
import org.asmjava5.Authenticate.data.dto.request.LogoutDtoRequest;
import org.asmjava5.Authenticate.data.dto.response.IntrospectDtoResponse;
import org.asmjava5.Authenticate.data.dto.response.LoginDtoResponse;
import org.asmjava5.Authenticate.service.LoginService;
import org.asmjava5.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    @PostMapping("/login")
    ApiResponse<LoginDtoResponse> login(@RequestBody LoginDtoRequest request) throws JOSEException {
        return ApiResponse.<LoginDtoResponse>builder()
                .status(HttpStatus.OK.value())
                .success(true)
                .data(loginService.authenticate(request))
                .build();
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
}
