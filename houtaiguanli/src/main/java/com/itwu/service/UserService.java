package com.itwu.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itwu.entity.User;

public interface UserService extends IService<User> {
    IPage<User> getPage(int current,int size,User user);
    IPage<User> getClassPage(int current,int size,User user);
    IPage<User> getAwardPage(int current, int size, User user);
}
