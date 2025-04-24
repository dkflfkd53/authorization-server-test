package com.founderz.authorizationservertest.controller;

import com.founderz.authorizationservertest.controller.dto.SignupRequest;
import com.founderz.authorizationservertest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public void signup(@RequestBody SignupRequest request) {
        userService.signup(request);
    }
}
