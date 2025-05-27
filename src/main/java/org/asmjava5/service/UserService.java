package org.asmjava5.service;

import org.asmjava5.data.dto.request.UserDtoRequest;
import org.asmjava5.data.dto.response.UserDtoResponse;
import org.asmjava5.exception.AppException;

import java.text.ParseException;
import java.util.List;

public interface UserService {
    List<UserDtoResponse> getUsers() throws AppException;
    UserDtoResponse getUserByUserName(String username) throws AppException;
    Boolean saveUser(UserDtoRequest userDtoRequest) throws AppException;
    Boolean deleteUserByUserName(String username) throws AppException;
}
