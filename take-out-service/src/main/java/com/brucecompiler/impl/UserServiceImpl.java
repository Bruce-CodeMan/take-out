package com.brucecompiler.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.brucecompiler.UserService;
import com.brucecompiler.constant.MessageConstant;
import com.brucecompiler.constant.StatusCodeConstant;
import com.brucecompiler.constant.WeChatConstant;
import com.brucecompiler.dto.UserLoginDTO;
import com.brucecompiler.entity.User;
import com.brucecompiler.exception.LoginFailedException;
import com.brucecompiler.mapper.UserMapper;
import com.brucecompiler.properties.WeChatProperties;
import com.brucecompiler.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final WeChatProperties weChatProperties;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, WeChatProperties weChatProperties) {
        this.userMapper = userMapper;
        this.weChatProperties = weChatProperties;
    }

    @Override
    public User login(UserLoginDTO userLoginDTO) {
        // 1. 通过HttpClient, 构造登录凭证校验请求
        Map<String, String> map = new HashMap<>();
        map.put(WeChatConstant.PARAM_APP_ID, weChatProperties.getAppId());
        map.put(WeChatConstant.PARAM_SECRET, weChatProperties.getSecret());
        map.put(WeChatConstant.PARAM_JS_CODE, userLoginDTO.getCode());
        map.put(WeChatConstant.PARAM_GRANT_TYPE, WeChatConstant.GRANT_TYPE_AUTHORIZATION_CODE);

        // 调用HttpClient发送请求
        String res = HttpClientUtil.doGet(WeChatConstant.SESSION_URL, map);
        log.info("res: {}", res);

        // 2. 解析响应结果, 获取openid
        JSONObject jsonObject = JSON.parseObject(res);
        String openid = (String) jsonObject.get("openid");
        if(openid == null) {
            throw new LoginFailedException(StatusCodeConstant.FAILURE, MessageConstant.USER_NOT_LOGIN);
        }

        // 3. 判断是否是新用户
        User user = userMapper.selectByOpenId(openid);

        // 4. 如果是新用户, 初始化用户数据到user表中
        if(user == null) {
            user = new User();
            user.setOpenid(openid);
            user.setCreateTime(LocalDateTime.now());
            user.setName(openid.substring(0, 5));
            userMapper.insert(user);
        }

        // 5. 否则直接返回user数据
        return user;
    }
}
