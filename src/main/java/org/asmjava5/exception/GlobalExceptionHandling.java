package org.asmjava5.exception;

import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.asmjava5.common.ApiResponse;
import org.asmjava5.enums.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandling {
    private void logError(Exception e, HttpServletRequest request) {
        log.error("Failed to call API " + request.getRequestURI() + " : " + e);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handleAppException(AppException e, HttpServletRequest request) {
        logError(e, request);
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.badRequest().body(
                ApiResponse.builder()
                        .status(errorCode.getStatus())
                        .message(errorCode.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(value = BadSqlGrammarException.class)
    ResponseEntity<ApiResponse> handleBadSqlGrammarException(BadSqlGrammarException e, HttpServletRequest request) {
        logError(e, request);
        return ResponseEntity.badRequest().body(
                ApiResponse.builder()
                        .status(ErrorCode.BAD_SQL.getStatus())
                        .message(ErrorCode.BAD_SQL.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handleException(Exception e, HttpServletRequest request) {
        logError(e, request);
        return ResponseEntity.badRequest().body(
                ApiResponse.builder()
                        .status(ErrorCode.UNCAUGHT_EXCEPTION.getStatus())
                        .message(ErrorCode.UNCAUGHT_EXCEPTION.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(value = JOSEException.class)
    ResponseEntity<ApiResponse> handleJOSEException(JOSEException e, HttpServletRequest request) {
        logError(e, request);
        return ResponseEntity.badRequest().body(
                ApiResponse.builder()
                        .status(ErrorCode.CANNOT_SIGN_JWT.getStatus())
                        .message(ErrorCode.CANNOT_SIGN_JWT.getMessage())
                        .build()
        );
    }
}
