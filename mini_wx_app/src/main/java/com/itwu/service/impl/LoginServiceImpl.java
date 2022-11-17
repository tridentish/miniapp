package com.itwu.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.itwu.entity.R;
import com.itwu.entity.User;
import com.itwu.service.LoginService;
import com.itwu.service.UserService;
import com.itwu.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserService userService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

//登入成功
    @Override
    public R login(User administrator) {
        System.out.println(administrator.getId());
        if(administrator.getId()!=null) {
            String token = JWTUtils.createToken(administrator.getId());
            redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(administrator), 1, TimeUnit.DAYS);
            return new R(true,token,"登入成功");
        }
        return null;
    }

    @Override
    public R checkToken(String token) {
        if (StringUtils.isBlank(token)) {
            return new R(false,"未登入");
        }

        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);
        if (stringObjectMap == null) {
            return new R(false,"未登入");

        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)) {
            return new R(false,"未登入");
        }

        User user = JSON.parseObject(userJson, User.class);

        return new R(true,user,"已登入");
    }

    @Override
    public R logout(String token) {
        redisTemplate.delete("TOKEN_" + token);
        return new R(true,"登出成功");
    }

    @Override
    public R register(User user) {
        return null;
    }
}
