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
        try {
            return ResponseEntity.ok(
                     ApiResponse.builder()
                             .status(200)
                             .message("Success")
                             .data(userService.getUsers())
                             .build()
            );
        } catch (Exception e) {
            log.error("Error: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body( ApiResponse.builder()
                            .status(500)
                            .message(e.getMessage())
                            .build());
        }
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

    @PostMapping("/delete/{name}")
    public ResponseEntity<?> delete(@RequestParam("name")String name){
        try{
            return ResponseEntity.ok(
                    ApiResponse.builder()
                            .status(200)
                            .message("Delete success")
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponse.builder()
                            .status(500)
                            .message("Fail to delete"+ e.getMessage())
                            .build()
            );
        }
    }
}
