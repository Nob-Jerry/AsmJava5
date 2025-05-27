package org.asmjava5.Authenticate.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.asmjava5.Authenticate.data.dto.request.IntrospectDtoRequest;
import org.asmjava5.Authenticate.data.dto.request.LoginDtoRequest;
import org.asmjava5.Authenticate.data.dto.request.LogoutDtoRequest;
import org.asmjava5.Authenticate.data.dto.response.IntrospectDtoResponse;
import org.asmjava5.Authenticate.data.dto.response.LoginDtoResponse;
import org.asmjava5.Authenticate.data.entity.InvalidToken;
import org.asmjava5.Authenticate.repository.InvalidTokenRepository;
import org.asmjava5.data.entity.User;
import org.asmjava5.enums.ErrorCode;
import org.asmjava5.exception.AppException;
import org.asmjava5.repository.UserRepository;
import org.asmjava5.utils.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoginImpl implements LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final InvalidTokenRepository invalidTokenRepository;

    @Value("${jwt.secret-key}")
    protected String SECRET_KEY;

    @Override
    public LoginDtoResponse authenticate(LoginDtoRequest request) throws JOSEException, ParseException {
        String username = request.getUsername();

        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_EMPTY));

        boolean authenticated = passwordEncoder.matches(request.getPassword()
                , user.getPassword());
        if (!authenticated) throw new AppException(ErrorCode.PASSWORD_INCORRECT);

        return LoginDtoResponse.builder()
                .token(generateAccessToken(user))
                .build();
    }

    @Override
    public IntrospectDtoResponse introspect(IntrospectDtoRequest request) throws JOSEException, ParseException {
        String accessToken = request.getToken();
        verifyJWT(accessToken);
        return IntrospectDtoResponse.builder()
                .isValid(true)
                .build();
    }

    @Override
    public Void logout(LogoutDtoRequest request) throws ParseException, JOSEException {
        String accessToken = request.getToken();
        var signedJWT = verifyJWT(accessToken);
        String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var exp = DateUtils.convertDateToTimestamp(expirationTime);

        InvalidToken invalidToken= InvalidToken.builder()
                .invalidTokenId(jwtId)
                .expTime(exp)
                .build();
        invalidTokenRepository.save(invalidToken);
        return null;
    }

    private SignedJWT verifyJWT(String accessToken) throws ParseException, JOSEException {

            SignedJWT signedJWT = SignedJWT.parse(accessToken);

            JWSVerifier verifier = new MACVerifier(SECRET_KEY.getBytes());

            Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

            boolean isExpired = expirationTime.before(new Date());
            boolean isVerified = signedJWT.verify(verifier);

            String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
            boolean isInvalidLogout = invalidTokenRepository
                    .existsInvalidTokenByInvalidTokenId(jwtId);

            if (!(isExpired && isVerified && !isInvalidLogout)) {
                throw new AppException(ErrorCode.INVALID_TOKEN);
            }

            return signedJWT;
    }

    private String generateAccessToken(User user) throws JOSEException {
        String roleName = user.getRole();
        String userName = user.getUsername();

        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(userName)
                .jwtID(generateUUID())
                .issuer("Jerry.com")
                .claim("scope", roleName)
                .issueTime(new Date())
                .expirationTime(Date
                        .from(Instant.now()
                                .plus(1, ChronoUnit.HOURS)))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
        return jwsObject.serialize();
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
