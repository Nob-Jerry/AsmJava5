package org.asmjava5.service;

import org.asmjava5.data.dto.request.UserDtoRequest;
import org.asmjava5.data.dto.request.update.UserUpdateRequest;
import org.asmjava5.data.dto.response.UserDtoResponse;
import org.asmjava5.exception.AppException;
import org.springframework.data.repository.query.Param;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface UserService {
    List<UserDtoResponse> getUsers();
    UserDtoResponse getUserByUserName(String username) ;
    Boolean saveUser(UserDtoRequest userDtoRequest) ;
    Boolean deleteUserByUserName(String username) ;
    Boolean updateUser(UserDtoRequest userDtoRequest) ;
    Boolean userUpdateRequest(UserUpdateRequest userUpdateRequest) ;
}
