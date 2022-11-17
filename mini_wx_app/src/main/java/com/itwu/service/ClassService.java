package com.itwu.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itwu.entity.Classs;

public interface ClassService extends IService<Classs> {
    IPage<Classs> getPage(int current, int size, Classs classs);
}
