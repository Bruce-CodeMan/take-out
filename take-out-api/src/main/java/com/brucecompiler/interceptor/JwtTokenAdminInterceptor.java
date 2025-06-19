package com.brucecompiler.interceptor;

import io.jsonwebtoken.Claims;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.brucecompiler.constant.JwtClaimsConstant;
import com.brucecompiler.constant.StatusCodeConstant;
import com.brucecompiler.properties.JwtProperties;
import com.brucecompiler.utils.JwtUtil;

@Component
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    private final JwtProperties jwtProperties;

    @Autowired
    public JwtTokenAdminInterceptor(final JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }


    @Override
    public boolean preHandle(
            @Nullable HttpServletRequest request,
            @Nullable HttpServletResponse response,
            @Nullable Object handler
    ) throws Exception {
        //1. Skip non-handler methods
        if(!(handler instanceof HandlerMethod)) return false;

        // 2. Ensure request and response are not null
        if (request == null || response == null) {
            handleUnauthorized(response);
            return false;
        }

        // 3. Get the token from the Request Header
        String token = request.getHeader(jwtProperties.getAdminTokenName());
        if(token == null || token.isEmpty()) {
            handleUnauthorized(response);
            return false;
        }

        // 4. Validate the token
        try{
            Claims claims = JwtUtil.parseJWT(token, jwtProperties.getAdminSecretKey());
            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_IP).toString());
            return true;
        } catch (Exception e) {
            handleUnauthorized(response);
            return false;
        }

    }

    /**
     * Handles unauthorized access by setting the appropriate response status
     *
     * @param response The HTTP response
     */
    private void handleUnauthorized(@Nullable HttpServletResponse response) {
        if(response != null) response.setStatus(StatusCodeConstant.UNAUTHORIZED);
    }


}
