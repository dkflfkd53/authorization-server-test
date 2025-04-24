package com.founderz.authorizationservertest.service;

import com.founderz.authorizationservertest.controller.dto.ClientRegistrationRequest;
import com.founderz.authorizationservertest.repository.JpaRegisteredClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final JpaRegisteredClientRepository jpaRegisteredClientRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void registerClient(@RequestBody ClientRegistrationRequest request) {
        RegisteredClient client = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId(request.clientId())
                .clientSecret(passwordEncoder.encode(request.clientSecret())) // 또는 암호화 필요
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUris(uris -> uris.addAll(request.redirectUris()))
                .scopes(scopes -> scopes.addAll(request.scopes()))
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();

        jpaRegisteredClientRepository.save(client);
    }
}
