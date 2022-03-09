package com.solncev.controller;

import com.solncev.dto.CreateUserDto;
import com.solncev.dto.UserDto;
import com.solncev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    @ResponseBody
    public Iterable<UserDto> getAll(@RequestParam(value = "name", required = false) Optional<String> name) {
        return name.isEmpty() ? userService.getAll() : userService.getAllByName(name.get());
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public UserDto get(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PostMapping("/user")
    @ResponseBody
    public UserDto createUser(@Valid @RequestBody CreateUserDto user) {
        return userService.save(user);
    }

    @GetMapping("/user/stepan")
    @ResponseBody
    public Iterable<UserDto> getAllStepan() {
        return userService.getAllStepan();
    }

    @PostMapping("/sign_up")
    public String signUp(@ModelAttribute(name = "user") CreateUserDto userDto) {
        userService.save(userDto);
        return "sign_up_success";
    }
}
