package org.asmjava5.Authenticate.service;

import com.nimbusds.jose.JOSEException;
import org.asmjava5.Authenticate.data.dto.request.IntrospectDtoRequest;
import org.asmjava5.Authenticate.data.dto.request.LoginDtoRequest;
import org.asmjava5.Authenticate.data.dto.request.LogoutDtoRequest;
import org.asmjava5.Authenticate.data.dto.response.IntrospectDtoResponse;
import org.asmjava5.Authenticate.data.dto.response.LoginDtoResponse;
import org.springframework.stereotype.Service;

import java.text.ParseException;

public interface LoginService {
    LoginDtoResponse authenticate(LoginDtoRequest request) throws JOSEException;
    IntrospectDtoResponse introspect(IntrospectDtoRequest request) throws JOSEException, ParseException;
    Void logout(LogoutDtoRequest request) throws ParseException, JOSEException;
}
