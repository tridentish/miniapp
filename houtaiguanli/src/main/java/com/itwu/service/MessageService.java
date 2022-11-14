package com.itwu.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itwu.entity.Message;

public interface MessageService extends IService<Message> {
    IPage<Message> getPage(int current,int size);
}
