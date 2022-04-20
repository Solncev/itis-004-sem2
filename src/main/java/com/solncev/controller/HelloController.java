package com.solncev.controller;

import com.solncev.aspect.Loggable;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@Controller
public class HelloController {

    @ApiOperation(value = "Returns hello message", produces = "application/json")
    @GetMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam Optional<String> name) {
        return String.format("Hello, %s!", name.orElse("name"));
    }

    @ApiOperation(value = "Returns index page", produces = "text/html")
    @GetMapping("")
    @Loggable
    public String getIndexPage() {
        return "index";
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

    @ApiOperation(value = "Returns home page", produces = "text/html")
    @GetMapping("/home")
    @Loggable
    public String getHome(HttpServletRequest httpServletRequest) {
        String currentPrincipalName = httpServletRequest.getUserPrincipal().getName();
        return "home";
    }
}
