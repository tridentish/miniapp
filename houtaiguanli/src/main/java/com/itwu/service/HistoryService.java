package com.itwu.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itwu.entity.History;

public interface HistoryService extends IService<History> {
    IPage<History> getPage(int current,int size,History history);
}
