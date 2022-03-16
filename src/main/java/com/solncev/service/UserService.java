package com.solncev.service;


import com.solncev.dto.CreateUserDto;
import com.solncev.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getByEmail(String email);

    UserDto getById(Integer id);

    List<UserDto> getAll();

    UserDto signUp(CreateUserDto createUserDto, String url);

    List<UserDto> getAllStepan();

    List<UserDto> getAllByName(String name);

    boolean verify(String verificationCode);

    void sendVerificationMail(String mail, String name, String code, String url);
}
