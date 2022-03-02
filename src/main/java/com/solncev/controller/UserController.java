package com.solncev.controller;

import com.solncev.dto.CreateUserDto;
import com.solncev.dto.UserDto;
import com.solncev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public Iterable<UserDto> getAll(@RequestParam(value = "name", required = false) Optional<String> name) {
        return name.isEmpty() ? userService.getAll() : userService.getAllByName(name.get());
    }

    @GetMapping("/user/{id}")
    public UserDto get(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PostMapping("/user")
    public UserDto createUser(@Valid @RequestBody CreateUserDto user) {
        return userService.save(user);
    }

    @GetMapping("/user/stepan")
    public Iterable<UserDto> getAllStepan() {
        return userService.getAllStepan();
    }
}
