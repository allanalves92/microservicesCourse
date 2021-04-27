package com.springbank.user.oauth20;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.security.oauth2.config.annotation.web.configuration.*;

@SpringBootApplication
@EnableAuthorizationServer
public class OAuth2Application {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2Application.class, args);
    }

}