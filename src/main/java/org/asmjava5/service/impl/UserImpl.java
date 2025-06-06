package org.asmjava5.service.impl;

import lombok.RequiredArgsConstructor;
import org.asmjava5.data.dto.request.UserDtoRequest;
import org.asmjava5.data.dto.request.update.UserUpdateRequest;
import org.asmjava5.data.dto.response.UserDtoResponse;
import org.asmjava5.convert.UserMapstruct;
import org.asmjava5.data.entity.User;
import org.asmjava5.enums.ErrorCode;
import org.asmjava5.exception.AppException;
import org.asmjava5.repository.UserRepository;
import org.asmjava5.service.CartService;
import org.asmjava5.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserImpl implements UserService {

    private final UserRepository userRepository;
    private final CartService cartService;
    private final PasswordEncoder passwordEncoder;

    private final UserMapstruct userMapstruct;

    @Override
    public List<UserDtoResponse> getUsers() {
        var listUserEntity = userRepository.findAll();
        if (listUserEntity.isEmpty()) throw new AppException(ErrorCode.FAIL_GET_LIST);
        return userMapstruct.toUserDTOResponseList(listUserEntity);
    }

    @Override
    public UserDtoResponse getUserByUserName(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_EMPTY));
        return userMapstruct.toUserDTOResponse(user);
    }

    @Override
    @Transactional
    public Boolean saveUser(UserDtoRequest userDtoRequest) {
        Optional<User> request = userRepository.findByUsername(userDtoRequest.getUsername());
        if(request.isEmpty()){
            var user = userMapstruct.toUser(userDtoRequest);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole("USER");
            user.setCreatedAt(new Date());
            user.setIsActive(true);
            userRepository.save(user);
            cartService.createCart(user.getUserId());
            return true;
        }else if (request.get().getUsername().equals(userDtoRequest.getUsername())){
            throw new AppException(ErrorCode.USERNAME_EXIST);
        }else if(request.get().getEmail().equals(userDtoRequest.getEmail())){
            throw new AppException(ErrorCode.USER_MAIL_EXIST);
        }else {
            throw new AppException(ErrorCode.USER_ALREADY_EXIST);
        }
    }


    @Override
    @Transactional
    public Boolean deleteUserByUserName(String username) {
        User userEntity = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_EMPTY));
        if (userEntity != null) {
            userRepository.delete(userEntity);
            return true;
        } else {
            throw new AppException(ErrorCode.FAIL_DELETE);
        }
    }


    @Override
    @Transactional
    public Boolean updateUser(UserDtoRequest userDtoRequest) {
        User user = userMapstruct.toUser(userDtoRequest);
        User userEntity = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new AppException(ErrorCode.USER_EMPTY));
        if (userEntity != null) {
            userRepository.updateUser(user);
            return true;
        } else {
            throw new AppException(ErrorCode.FAIL_TO_SAVE_UPDATE);
        }
    }

    @Override
    public Boolean userUpdateRequest(UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findByUsername(userUpdateRequest.getUsername()).orElseThrow(() -> new AppException(ErrorCode.USER_EMPTY));
        User update = userMapstruct.toUserM(userUpdateRequest);
        userRepository.updateUser(update);
        return true;
    }
}
