package org.asmjava5.Authenticate.controller;

import lombok.RequiredArgsConstructor;
import org.asmjava5.Authenticate.service.VerifyEmailService;
import org.asmjava5.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/verify")
@RequiredArgsConstructor
public class VerifyEmailController {
    private final VerifyEmailService verifyEmailService;

    @GetMapping("/email")
    public ResponseEntity<?> verifyEmail(@RequestParam("activationToken") String activationToken) {
        boolean result = verifyEmailService.verifyEmailByToken(activationToken);
        if (result) {
            return ResponseEntity.ok(ApiResponse.builder()
                    .status(200)
                    .success(true)
                    .message("Email verified")
                    .build());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.builder()
                .status(4000)
                .success(false)
                .message("Invalid activation token")
                .build());
    }
}
