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
        Client entity = new Client();
        entity.setClientId(registeredClient.getClientId());
        entity.setClientSecret(registeredClient.getClientSecret());
        entity.setRedirectUris(registeredClient.getRedirectUris());
        entity.setScopes(registeredClient.getScopes());

        clientRepository.save(entity);
    }

    @Override
    public RegisteredClient findById(String id) {
        // ID는 일반적으로 UUID지만 여기선 생략
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
