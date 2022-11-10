package com.itwu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itwu.entity.User;
import com.itwu.mapper.UserMapper;
import com.itwu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage<User> getPage(int current, int size, User user) {
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<User>();
        qw.like(Strings.isNotEmpty(user.getStudentNum()),User::getStudentNum,user.getStudentNum());
        qw.like(Strings.isNotEmpty(user.getStudentName()),User::getStudentName,user.getStudentName());
        IPage page =new Page(current,size);
        userMapper.selectPage(page,qw);
        return page;
    }
}
