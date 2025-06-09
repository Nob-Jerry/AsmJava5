package org.asmjava5.Authenticate.service;

import com.nimbusds.jose.JOSEException;
import org.asmjava5.Authenticate.data.dto.request.GoogleLoginRequest;
import org.asmjava5.Authenticate.data.dto.request.IntrospectDtoRequest;
import org.asmjava5.Authenticate.data.dto.request.LoginDtoRequest;
import org.asmjava5.Authenticate.data.dto.request.LogoutDtoRequest;
import org.asmjava5.Authenticate.data.dto.response.GoogleLoginResponse;
import org.asmjava5.Authenticate.data.dto.response.IntrospectDtoResponse;
import org.asmjava5.Authenticate.data.dto.response.LoginDtoResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

public interface LoginService {
    LoginDtoResponse authenticate(LoginDtoRequest request) throws JOSEException, ParseException;
    GoogleLoginResponse googleLogin(GoogleLoginRequest request) throws JOSEException, ParseException, GeneralSecurityException, IOException;
    IntrospectDtoResponse introspect(IntrospectDtoRequest request) throws JOSEException, ParseException;
    Void logout(LogoutDtoRequest request) throws ParseException, JOSEException;

}
