package com.founderz.authorizationservertest.controller;

import com.founderz.authorizationservertest.controller.dto.SignupRequest;
import com.founderz.authorizationservertest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public void signup(@RequestBody SignupRequest request) {
        userService.signup(request);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
