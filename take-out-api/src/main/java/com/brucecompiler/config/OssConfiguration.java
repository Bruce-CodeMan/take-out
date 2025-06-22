package com.brucecompiler.config;

import com.brucecompiler.properties.OSSProperties;
import com.brucecompiler.utils.OssUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OssConfiguration {

    @Bean
    public OssUtil ossUtil(OSSProperties ossProperties) {
        return new OssUtil(
                ossProperties.getEndpoint(),
                ossProperties.getAccessKey(),
                ossProperties.getAccessSecret(),
                ossProperties.getBucketName()
        );
    }
}
