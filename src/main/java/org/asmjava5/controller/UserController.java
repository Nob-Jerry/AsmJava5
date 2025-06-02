package org.asmjava5.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asmjava5.common.ApiResponse;
import org.asmjava5.data.dto.request.UserDtoRequest;
import org.asmjava5.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() throws SQLException {
            return ResponseEntity.ok(
                     ApiResponse.builder()
                             .status(200)
                             .message("Success")
                             .data(userService.getUsers())
                             .build()
            );
    }
    @GetMapping("/id")
    public ResponseEntity<?> getUserName(@RequestParam("username") String username) throws SQLException {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .status(200)
                        .message("Success")
                        .data(userService.getUserByUserName(username))
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
    public ResponseEntity<?> update(@RequestBody UserDtoRequest userDtoRequest) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .data(userService.updateUser(userDtoRequest))
                        .message("Successfully saved user")
                        .build()
        );

    }

    @PostMapping("/delete/{username}")
    public ResponseEntity<?> delete(@RequestParam("username")String username) {
        userService.deleteUserByUserName(username);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .status(200)
                        .message("Delete success")
                        .build()
        );
    }
}
