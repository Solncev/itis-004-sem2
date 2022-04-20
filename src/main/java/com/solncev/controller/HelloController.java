package com.solncev.controller;

import com.solncev.aspect.Loggable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class HelloController {

    @Operation(summary = "Returns hello message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hello message for given name",
                    content = {
                            @Content(mediaType = "application/json")
                    }
            )
    })
    @GetMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam Optional<String> name) {
        return String.format("Hello, %s!", name.orElse("name"));
    }

    @Operation(summary = "Returns index page")
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

    @Operation(summary = "Returns home page")
    @GetMapping("/home")
    @Loggable
    public String getHome(HttpServletRequest httpServletRequest) {
        String currentPrincipalName = httpServletRequest.getUserPrincipal().getName();
        return "home";
    }
}
