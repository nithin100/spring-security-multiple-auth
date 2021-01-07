package com.nithin.resourceserver.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.OpaqueTokenAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.introspection.NimbusOpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.stereotype.Component;

public class OAuthTokenIntrospector implements OpaqueTokenIntrospector  {

    private OpaqueTokenIntrospector tokenIntrospector;

    public OAuthTokenIntrospector(String tokenInfoUri, String clientId, String clientSecret) {
        this.tokenIntrospector = new NimbusOpaqueTokenIntrospector(tokenInfoUri, clientId, clientSecret);
    }

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        OAuth2AuthenticatedPrincipal authenticatedPrincipal = this.tokenIntrospector.introspect(token);
        return authenticatedPrincipal;
    }
}
