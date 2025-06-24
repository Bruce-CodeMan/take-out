package com.brucecompiler.interceptor;

import com.brucecompiler.constant.JwtClaimsConstant;
import com.brucecompiler.constant.StatusCodeConstant;
import com.brucecompiler.context.BaseContext;
import com.brucecompiler.properties.JwtProperties;
import com.brucecompiler.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class JwtTokenUserInterceptor implements HandlerInterceptor {

    private final JwtProperties jwtProperties;

    @Autowired
    public JwtTokenUserInterceptor(final JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Override
    public boolean preHandle(
            @Nullable HttpServletRequest request,
            @Nullable HttpServletResponse response,
            @Nullable Object handler
    ) throws Exception {
        // 1. Skip non-handler methods
        if(!(handler instanceof HandlerMethod)) return false;

        // 2. Ensure request and response are not null
        if(request == null || response == null) {
            handleUnauthorized(response);
            return false;
        }

        // 3. Get the token from the request Header
        String token = request.getHeader(jwtProperties.getUserTokenName());
        if(token == null || token.isEmpty()) {
            handleUnauthorized(response);
            return false;
        }

        // 4. Validate the token
        try{
            Claims claims = JwtUtil.parseJWT(token, jwtProperties.getUserSecretKey());
            Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
            BaseContext.setCurrentId(userId);
        }catch (Exception e){
            handleUnauthorized(response);
            return false;
        }
        return true;
    }

    private void handleUnauthorized(@Nullable HttpServletResponse response){
        if(response != null) response.setStatus(StatusCodeConstant.UNAUTHORIZED);
    }
}
