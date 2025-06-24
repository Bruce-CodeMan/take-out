package com.brucecompiler.controller.user;

import com.brucecompiler.UserService;
import com.brucecompiler.constant.JwtClaimsConstant;
import com.brucecompiler.dto.UserLoginDTO;
import com.brucecompiler.entity.User;
import com.brucecompiler.properties.JwtProperties;
import com.brucecompiler.result.Result;
import com.brucecompiler.utils.JwtUtil;
import com.brucecompiler.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user/user")
public class UserController {

    private final UserService userService;
    private final JwtProperties jwtProperties;

    @Autowired
    public UserController(UserService userService, JwtProperties jwtProperties) {
        this.userService = userService;
        this.jwtProperties = jwtProperties;
    }

    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {

        // 1. 调用service的登录方法, 获取user
        User user = userService.login(userLoginDTO);

        // 2. 如果登录成功, 生成令牌JWT
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.generateJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserExpirationTime(),
                claims
        );

        // 3. 构造UserLoginVO对象
        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .token(token).build();
        return Result.success(userLoginVO);
    }

}
