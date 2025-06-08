package org.asmjava5.Authenticate.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.asmjava5.Authenticate.repository.InvalidTokenRepository;
import org.asmjava5.Authenticate.service.VerifyEmailService;
import org.asmjava5.data.entity.User;
import org.asmjava5.enums.ErrorCode;
import org.asmjava5.exception.AppException;
import org.asmjava5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerifyEmailImpl implements VerifyEmailService {
    private final UserRepository userRepository;
    private final InvalidTokenRepository invalidTokenRepository;

    @Value("${jwt.secret-key}")
    protected String SECRET_KEY;


    @Override
    public boolean verifyEmailByToken(String activationToken) {
        try {
            SignedJWT signedJWT = verifyJWT(activationToken);
            String email = signedJWT.getJWTClaimsSet().getSubject();
            User user = userRepository.findUserByEmail(email);
            if (user == null) {
                throw new AppException(ErrorCode.USER_EMPTY);
            }
            if (Boolean.TRUE.equals(user.getIsVerified())) {
                throw new AppException(ErrorCode.USER_ALREADY_VERIFIED);
            }
            user.setIsVerified(true);
            user.setActivationToken(null);
            userRepository.save(user);
            return true;
        } catch (ParseException | JOSEException e) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
    }


    private SignedJWT verifyJWT(String activationToken) throws ParseException, JOSEException {

        SignedJWT signedJWT = SignedJWT.parse(activationToken);

        JWSVerifier verifier = new MACVerifier(SECRET_KEY.getBytes());

        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        boolean isExpired = expirationTime.before(new Date());
        boolean isVerified = signedJWT.verify(verifier);

        String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
        boolean isInvalidLogout = invalidTokenRepository
                .existsInvalidTokenByInvalidTokenId(jwtId);

        if (isExpired || !isVerified || isInvalidLogout) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        return signedJWT;
    }

    public String generateVerifyToken(User user) throws JOSEException {
        String role = user.getRole();
        String email = user.getEmail();
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                .jwtID(generateUUID())
                .issuer("Jerry.com")
                .claim("scope", role)
                .issueTime(new Date())
                .expirationTime(Date
                        .from(Instant.now()
                                .plus(10, ChronoUnit.MINUTES)))
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
