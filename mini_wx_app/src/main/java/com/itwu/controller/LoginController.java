package com.itwu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.itwu.entity.R;
import com.itwu.entity.User;
import com.itwu.entity.WXAuth;
import com.itwu.entity.WxUserInfo;
import com.itwu.mapper.UserMapper;
import com.itwu.service.LoginService;
import com.itwu.service.UserService;
import com.itwu.service.impl.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Wrapper;

@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private WxService wxService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginService loginService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    //getsessionid
    @GetMapping("/getsessionid")
    public R getSessionId(String code){

        return userService.getSessionId(code);
    }

    @PostMapping("/login")
    public R authLogin(@RequestBody WXAuth wxAuth){
        try {
            System.out.println(wxAuth);
            String json= wxService.wxDecrypt(wxAuth.getEncryptedData(),wxAuth.getSessionId(),wxAuth.getIv());
            System.out.println(json);
            WxUserInfo wxUserInfo = JSON.parseObject(json, WxUserInfo.class);
            String jsonw =  redisTemplate.opsForValue().get("wx_session_id" + wxAuth.getSessionId());
            JSONObject jsonObject = JSON.parseObject(jsonw);
            String openId = (String) jsonObject.get("openid");
            System.out.println(wxUserInfo);
            User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getOpenId, openId));
            if (user==null){
                //注册
                User newUser=new User();
                newUser.setGender(wxUserInfo.getGender());
                newUser.setOpenId(openId);
                newUser.setHead(wxUserInfo.getAvatarUrl());
                newUser.setStatus(0);
                newUser.setWxUnionId(wxUserInfo.getUnionId());
                //save之后newUser里面自动更新为存入的数据信息
                userService.save(newUser);
                //user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getOpenId, openId));
                return loginService.login(newUser);
                //return loginService.login(user);
                //return null;
            }else{
                //登入
                return loginService.login(user);
                //return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new R(false,"异常");
    }

    @PostMapping("/update")
    public R updateMessage(@RequestBody User user){
        return new R(true,userService.updateById(user),"修改成功");
    }

}
