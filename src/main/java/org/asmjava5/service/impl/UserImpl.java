package org.asmjava5.service.impl;

import lombok.RequiredArgsConstructor;
import org.asmjava5.data.dto.request.UserDtoRequest;
import org.asmjava5.data.dto.response.UserDtoResponse;
import org.asmjava5.convert.UserMapstruct;
import org.asmjava5.enums.ErrorCode;
import org.asmjava5.exception.AppException;
import org.asmjava5.repository.UserRepository;
import org.asmjava5.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapstruct userMapstruct;

    @Override
    public List<UserDtoResponse> getUsers() {
        var listUserEntity = userRepository.findAll();
        if (listUserEntity.isEmpty()) throw new AppException(ErrorCode.FAIL_GET_LIST);
        return userMapstruct.toUserDTOResponseList(listUserEntity);
    }

    @Override
    public UserDtoResponse getUserByUserName(String username) {
        var userEntity = userRepository.findUserByUsername(username);
        if (userEntity == null)throw new AppException(ErrorCode.T_EMPTY);
        return userMapstruct.toUserDTOResponse(userEntity);
    }

    @Override
    @Transactional
    public Boolean saveUser(UserDtoRequest userDtoRequest) {
            var user = userMapstruct.toUser(userDtoRequest);
            if (userRepository.findUserByUsername(user.getUsername()) == null) {
                userRepository.save(user);
                return true;
            }else {
                throw new AppException(ErrorCode.FAIL_TO_SAVE_UPDATE);
            }
    }


    @Override
    @Transactional
    public Boolean deleteUserByUserName(String username) {
            var userEntity = userRepository.findUserByUsername(username);
            if (userEntity != null) {
                userRepository.delete(userEntity);
                return true;
            }else {
                throw new AppException(ErrorCode.FAIL_DELETE);
            }
    }


    @Override
    @Transactional
    public Boolean updateUser(UserDtoRequest userDtoRequest) {
            var user = userMapstruct.toUser(userDtoRequest);
            if (userRepository.findUserByUsername(user.getUsername()) != null) {
                userRepository.save(user);
                return false;
            }else {
                throw new AppException(ErrorCode.FAIL_TO_SAVE_UPDATE);
            }
    }
}
