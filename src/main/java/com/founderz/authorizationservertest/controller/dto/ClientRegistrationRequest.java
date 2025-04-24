package com.founderz.authorizationservertest.controller.dto;

import java.util.List;

public record ClientRegistrationRequest(
        String clientId,
        String clientSecret,
        List<String> redirectUris,
        List<String> scopes
) {
}