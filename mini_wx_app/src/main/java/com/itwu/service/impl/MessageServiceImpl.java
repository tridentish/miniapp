package com.itwu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itwu.entity.Message;
import com.itwu.mapper.MessageMapper;
import com.itwu.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Override
    public IPage<Message> getPage(int current, int size) {
        LambdaQueryWrapper<Message> qw = new LambdaQueryWrapper<Message>();
        IPage page =new Page(current,size);
        messageMapper.selectPage(page,qw);
        return page;
    }


}
