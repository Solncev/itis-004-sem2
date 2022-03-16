package com.solncev.controller;

import com.solncev.dto.CreateUserDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam Optional<String> name) {
        return String.format("Hello, %s!", name.orElse("name"));
    }

    @GetMapping("")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/sign_up")
    public String getSignUp(Model model) {
        model.addAttribute("user", new CreateUserDto());
        return "sign_up";
    }

//    @GetMapping("/home")
//    public String getHome(Principal principal) {
//        String currentPrincipalName = principal.getName();
//        return "home";
//    }

//    @GetMapping("/home")
//    public String getHome(Authentication authentication) {
//        String currentPrincipalName = authentication.getName();
//        return "home";
//    }

    @GetMapping("/home")
    public String getHome(HttpServletRequest httpServletRequest) {
        String currentPrincipalName = httpServletRequest.getUserPrincipal().getName();
        return "home";
    }
}
