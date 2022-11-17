package com.itwu.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itwu.entity.R;
import com.itwu.entity.User;
import com.itwu.entity.WXAuth;
import com.itwu.mapper.UserMapper;
import com.itwu.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Value("${wxmini.appid}")
    private String appid;

    @Value(("${wxmini.secret}"))
    private String secret;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public R getSessionId(String code) {
        String url ="https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";
        url = url.replace("{0}", appid).replace("{1}", secret).replace("{2}", code);
        String res = HttpUtil.get(url);
        String uuid=UUID.randomUUID().toString();
        redisTemplate.opsForValue().set("wx_session_id"+uuid,res,30, TimeUnit.MINUTES);

        return new R(true,uuid,"sessionId获取成功");
    }

    @Override
    public IPage<User> getClassPage(int current, int size, User user) {
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<User>();
        qw.like(Strings.isNotEmpty(user.getStudentNum()),User::getStudentNum,user.getStudentNum());
        qw.like(Strings.isNotEmpty(user.getStudentName()),User::getStudentName,user.getStudentName());
        qw.eq(User::getClassNum,user.getClassNum());
        IPage page =new Page(current,size);
        userMapper.selectPage(page,qw);
        return page;
    }

    @Override
    public R authLogin(WXAuth wxAuth){

        return null;
    }
}
