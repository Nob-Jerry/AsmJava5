package org.asmjava5.service.impl;

import org.asmjava5.data.entity.User;
import org.asmjava5.repository.UserRepository;
import org.asmjava5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
