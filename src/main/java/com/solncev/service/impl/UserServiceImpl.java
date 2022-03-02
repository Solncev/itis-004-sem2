package com.solncev.service.impl;

import com.solncev.dto.CreateUserDto;
import com.solncev.dto.UserDto;
import com.solncev.model.User;
import com.solncev.repository.UserRepository;
import com.solncev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto getByEmail(String email) {
        return null;
    }

    @Override
    public UserDto getById(Integer id) {
        return userRepository.findById(id).stream().map(UserDto::fromModel).findFirst().orElse(null);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserDto::fromModel).collect(Collectors.toList());
    }

    @Override
    public UserDto save(CreateUserDto user) {
        return UserDto.fromModel(userRepository.save(new User(user.getName(), user.getEmail())));
    }

    @Override
    public List<UserDto> getAllStepan() {
        return userRepository.findAllStepanUser().stream().map(UserDto::fromModel).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getAllByName(String name) {
        return userRepository.findAllByName(name).stream().map(UserDto::fromModel).collect(Collectors.toList());
    }
}
