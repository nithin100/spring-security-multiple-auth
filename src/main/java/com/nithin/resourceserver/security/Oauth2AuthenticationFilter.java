package com.nithin.resourceserver.security;

import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Oauth2AuthenticationFilter extends OncePerRequestFilter {

    private OpaqueTokenIntrospector opaqueTokenIntrospector;

    public Oauth2AuthenticationFilter(OpaqueTokenIntrospector opaqueTokenIntrospector){
        this.opaqueTokenIntrospector = opaqueTokenIntrospector;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String tokeyType = "Bearer";
        if(header != null){
            String token = header.replace(tokeyType, "");
            OAuth2AuthenticatedPrincipal principal =  this.opaqueTokenIntrospector.introspect(token);
            if(principal != null){
                Authentication authentication = new BearerTokenAuthentication(principal, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            chain.doFilter(request, response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().contains("/mobile");
    }
}
