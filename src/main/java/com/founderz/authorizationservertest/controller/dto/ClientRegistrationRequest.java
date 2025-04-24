package com.founderz.authorizationservertest.controller.dto;

import java.util.List;

public record ClientRegistrationRequest(
        String client_id,
        String client_secret,
        List<String> redirect_uris,
        List<String> scopes
) {
}