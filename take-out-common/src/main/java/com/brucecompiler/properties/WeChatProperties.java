package com.brucecompiler.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatProperties {

    private String appId;

    private String secret;

}
