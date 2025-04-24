package com.founderz.authorizationservertest.repository;

import com.founderz.authorizationservertest.entity.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaRegisteredClientRepository implements RegisteredClientRepository {

    private final ClientRepository clientRepository;

    @Override
    public void save(RegisteredClient registeredClient) {
        clientRepository.save(
                Client.builder()
                        .clientId(registeredClient.getClientId())
                        .clientSecret(registeredClient.getClientSecret())
                        .redirectUris(registeredClient.getRedirectUris())
                        .scopes(registeredClient.getScopes())
                        .build());
    }

    @Override
    public RegisteredClient findById(String id) {
        throw new UnsupportedOperationException("Find by ID not supported.");
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return clientRepository.findByClientId(clientId)
                .map(entity -> RegisteredClient.withId(UUID.randomUUID().toString())
                        .clientId(entity.getClientId())
                        .clientSecret(entity.getClientSecret())
                        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                        .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                        .redirectUris(uris -> uris.addAll(entity.getRedirectUris()))
                        .scopes(scopes -> scopes.addAll(entity.getScopes()))
                        .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                        .build()
                )
                .orElse(null);
    }
}
