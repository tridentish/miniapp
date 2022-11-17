package com.itwu.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itwu.entity.R;
import com.itwu.entity.User;
import com.itwu.entity.WXAuth;

public interface UserService extends IService<User> {
    R getSessionId(String code);
    IPage<User> getClassPage(int current,int size,User user);

    R authLogin(WXAuth wxAuth);
}
