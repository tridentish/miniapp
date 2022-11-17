package com.itwu.config;

import com.itwu.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader("Authorization");
        log.info("=================request start===========================");
        String requestURI = request.getRequestURI();
        log.info("request uri:{}", requestURI);
        log.info("request method:{}", request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");

        if(request.getRequestURI().equalsIgnoreCase("/user/login")){
            //System.out.println(request.getRequestURI()+" : 通过检查");
            return true;
        }
        if(request.getRequestURI().equalsIgnoreCase("/user/getsessionid")){
            //System.out.println(request.getRequestURI()+" : 通过检查");
            return true;
        }
        if (token == null) {
            return false;
        }
        boolean flag = loginService.checkToken(token).isFlag();
        if (flag == false) {
            return false;
        }
        //是登录状态，放行
        return true;
    }
}
