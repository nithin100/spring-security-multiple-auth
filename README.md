# spring-security-multiple-auth
This is a demonstration of using multiple authentication mechanisms for a spring boot resource server

# Disclaimer: 

This code is only a proof of concept and is no way a production standard. However it is a great way to start with mutiple authentication scenarios. The best possible way of using a framework is to respect the way in which a framework is built. With that said, the best way someone can extend this code is by using multiple AuthenticationProviders and have AuthenticationManger to authenticate. This is how the spring security was built. Please refer to spring security documentation to understand the internal framework contracts. 

# About the code:

In the code I have two types of controllers: /api and /mobile. These two resources are protected by JWT and OAuth respectively. In order to simulate JWT issuing, I have a piece of code that gets triggered once the app starts. The token gets printed onto the console for you to validate the security. However, in production you would have an AuthorizationServer to issue tokens. You can write your own AuthorizationServer or use third party providers like OKTA, ISAM etc. In order to simulate OAuth2 security I have my own Authroization server (you can find it here: https://github.com/nithin100/spring-security/tree/master/authorization-server) running on localhost. This server gives me a token that I can use for my resource server. However, the userinfo endpoint of my authorization server expects Bearer token in Authorization header which is a problem as the NimbusTokenIntrospector (which is used by my resource server) sends the token as part of request body and not header. This can be easily solved by creating an API in AuthroizationServer (Which I didn't).
