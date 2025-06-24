package com.brucecompiler.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {

    // admin management generate JWT token
    private String adminSecretKey;

    private Long expirationTime;

    private String adminTokenName;

    // user management generate JWT token
    private String userSecretKey;

    private Long userExpirationTime;

    private String userTokenName;
}
