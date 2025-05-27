package org.asmjava5.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asmjava5.common.ApiResponse;
import org.asmjava5.data.dto.request.UserDtoRequest;
import org.asmjava5.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ApiResponse<?> getAll() {

        return ApiResponse.builder()
                .success(true)
                .message("Success")
                .data(userService.getUsers())
                .build();
    }
    @PostMapping("/save")
    public ApiResponse<?> save(@RequestBody UserDtoRequest userDtoRequest) {
        return ApiResponse.builder()
                .success(true)
                .data(userService.saveUser(userDtoRequest))
                .message("Successfully saved user")
                .build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserDtoRequest userDtoRequest){
        Map<String, Object> resultMapAPI = new LinkedHashMap<>();
        try {
            resultMapAPI.put("status", 200);
            resultMapAPI.put("success", true);
            resultMapAPI.put("data", userService.saveUser(userDtoRequest));
        } catch (Exception e) {
            resultMapAPI.put("status", 500);
            resultMapAPI.put("success", false);
            resultMapAPI.put("message", e.getMessage());
            log.error("Fail to call API /user/update", e);
        }
        return ResponseEntity.ok(resultMapAPI);
    }

}
