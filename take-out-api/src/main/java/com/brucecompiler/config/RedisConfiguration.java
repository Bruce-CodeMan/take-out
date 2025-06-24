package com.brucecompiler.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        log.info("RedisTemplate");

        // 设置key的序列化器StringRedisSerializer, 默认是JdkSerializationRedisSerializer
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());

        // 注意: 不推荐修改value的序列化器
        // 通过工厂创建对象
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
