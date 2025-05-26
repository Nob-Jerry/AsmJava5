package org.asmjava5.service.impl;

import lombok.RequiredArgsConstructor;
import org.asmjava5.data.dto.response.UserDTOResponse;
import org.asmjava5.data.entity.User;
import org.asmjava5.convert.UserMapstruct;
import org.asmjava5.repository.UserRepository;
import org.asmjava5.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapstruct userMapstruct;

    @Override
    public List<UserDTOResponse> getUsers() {
        var listUserEntity = userRepository.findAll();
        return userMapstruct.toUserDTOResponseList(listUserEntity);
    }
}
