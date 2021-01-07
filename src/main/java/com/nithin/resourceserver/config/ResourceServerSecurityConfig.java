package com.nithin.resourceserver.config;

import com.nithin.resourceserver.security.JwtAuthenticationFilter;
import com.nithin.resourceserver.security.OAuthTokenIntrospector;
import com.nithin.resourceserver.security.Oauth2AuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class ResourceServerSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean(){
        return new JwtAuthenticationFilter();
    }

    @Bean
    public OpaqueTokenIntrospector opaqueTokenIntrospector(){
        return new OAuthTokenIntrospector("http://localhost:8080/uaa/user","stems_resource","stems_seceret");
    }

    @Bean
    public Oauth2AuthenticationFilter oauth2AuthenticationFilter(){
        return new Oauth2AuthenticationFilter(opaqueTokenIntrospector());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(oauth2AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
