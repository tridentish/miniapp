package com.itwu.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itwu.entity.Schedule;

public interface ScheduleService extends IService<Schedule> {
    IPage<Schedule> getPage(int current, int size);
}
