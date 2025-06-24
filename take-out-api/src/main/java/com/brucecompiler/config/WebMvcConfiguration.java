package com.brucecompiler.config;

import com.brucecompiler.interceptor.JwtTokenAdminInterceptor;
import com.brucecompiler.interceptor.JwtTokenUserInterceptor;
import com.brucecompiler.json.JacksonObjectMapper;
import com.brucecompiler.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    private final JwtTokenAdminInterceptor jwtTokenAdminInterceptor;
    private final JwtTokenUserInterceptor jwtTokenUserInterceptor;

    @Autowired
    public WebMvcConfiguration(
            JwtTokenAdminInterceptor jwtTokenAdminInterceptor,
            JwtTokenUserInterceptor jwtTokenUserInterceptor
    ) {
        this.jwtTokenAdminInterceptor = jwtTokenAdminInterceptor;
        this.jwtTokenUserInterceptor = jwtTokenUserInterceptor;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // Admin management interceptors
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/employee/login");

        // We-chat management interceptors
        registry.addInterceptor(jwtTokenUserInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/user/login")
                .excludePathPatterns("/user/shop/status");
    }

    /**
     * 扩展mvc框架的消息转换器
     */
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 创建一个消息转换器对象
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        // 设置对象转换器, 可以将Java对象转为json字符串
        converter.setObjectMapper(new JacksonObjectMapper());

        // 将我们自己的转换器放入spring MVC框架中
        converters.add(0, converter);
    }
}
