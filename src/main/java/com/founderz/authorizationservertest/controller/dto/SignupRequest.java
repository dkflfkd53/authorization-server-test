package com.founderz.authorizationservertest.controller.dto;

public record SignupRequest(
        String username,
        String password,
        String role
) {
}
