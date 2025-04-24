package com.founderz.authorizationservertest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AuthorizationServerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerTestApplication.class, args);
    }

}
