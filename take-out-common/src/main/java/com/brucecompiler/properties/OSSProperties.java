package com.brucecompiler.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "oss")
@Data
public class OSSProperties {

    private String endpoint;

    private String accessKey;

    private String accessSecret;

    private String bucketName;
}
