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
        if (listUserEntity.isEmpty()) throw new AppException(ErrorCode.LIST_USER_EMPTY);
        return userMapstruct.toUserDTOResponseList(listUserEntity);
    }

    @Override
    public UserDtoResponse getUserByUserName(String username) {
        var userEntity = userRepository.findUserByUsername(username);
        return userMapstruct.toUserDTOResponse(userEntity);
    }

    @Override
    @Transactional
    public Boolean saveUser(UserDtoRequest userDtoRequest) {
        var user = userMapstruct.toUser(userDtoRequest);
        if (userRepository.findUserByUsername(user.getUsername()) == null) {
            userRepository.save(user);
            return false;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean deleteUserByUserName(String username) {
        var userEntity = userRepository.findUserByUsername(username);
        if (userEntity != null) {
            userRepository.delete(userEntity);
            return true;
        }
        return false;
    }


    @Override
    @Transactional
    public Boolean updateUser(UserDtoRequest userDtoRequest) throws AppException {
        var user = userMapstruct.toUser(userDtoRequest);
        if (userRepository.findUserByUsername(user.getUsername()) != null) {
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
