package com.shake_match.alchomist.configures;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfigure {
    private String header;
    private String issuer;
    private String clientSecret;
    private int expirySeconds;
}
