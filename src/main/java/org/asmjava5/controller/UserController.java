package org.asmjava5.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asmjava5.common.ApiResponse;
import org.asmjava5.data.dto.request.UserDtoRequest;
import org.asmjava5.data.dto.response.UserDtoResponse;
import org.asmjava5.enums.ErrorCode;
import org.asmjava5.exception.AppException;
import org.asmjava5.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
            return ResponseEntity.ok(
                     ApiResponse.builder()
                             .status(200)
                             .message("Success")
                             .data(userService.getUsers())
                             .build()
            );
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody UserDtoRequest userDtoRequest) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .data(userService.saveUser(userDtoRequest))
                        .message("Successfully saved user")
                        .build()
        );
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserDtoRequest userDtoRequest){
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .data(userService.updateUser(userDtoRequest))
                        .message("Successfully saved user")
                        .build()
        );

    }

    @PostMapping("/delete/{name}")
    public ResponseEntity<?> delete(@RequestParam("name")String name){
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .status(200)
                            .message("Delete success")
                            .build()
            );
    }
}
