package com.itwu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itwu.entity.Administrator;
import com.itwu.entity.R;
import com.itwu.service.AdministratorService;
import com.itwu.service.LoginService;
import com.itwu.service.UserService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
@RequestMapping("/administrator")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;

    //员工登入
    //HttpServletRequest request,
    @PostMapping("/login")
    public R<Administrator> login( @RequestBody Administrator administrator){
        //加密
        String password=administrator.getPassword();
        //password=DigestUtils.md5DigestAsHex(password.getBytes());
        //查询用户名
        LambdaQueryWrapper<Administrator> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Administrator::getAccount,administrator.getAccount());
        Administrator tmp = administratorService.getOne(queryWrapper);
        if(tmp==null){
            return new R<>(false,"用户不存在");
        }
        //比对密码
        if(!tmp.getPassword().equals(password)){
            return new R<>(false,"密码错误");
        }

        //request.getSession().setAttribute("administrator",administrator.getAccount());

        return loginService.login(administrator);
    }

}
