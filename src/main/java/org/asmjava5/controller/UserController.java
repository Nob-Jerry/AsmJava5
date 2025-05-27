package org.asmjava5.controller;

import lombok.RequiredArgsConstructor;
import org.asmjava5.common.ApiResponse;
import org.asmjava5.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ApiResponse<?> getAll() {

        return ApiResponse.builder()
                .success(true)
                .data(userService.getUsers())
                .build();
    }
}
