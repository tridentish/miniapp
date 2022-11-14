package com.itwu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itwu.entity.R;
import com.itwu.entity.Schedule;
import com.itwu.entity.User;
import com.itwu.mapper.ScheduleMapper;
import com.itwu.service.ScheduleService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper,Schedule> implements ScheduleService{

    @Autowired
    private ScheduleMapper scheduleMapper;

    //分页显示
    @Override
    public IPage<Schedule> getPage(int current, int size) {
        LambdaQueryWrapper<Schedule> qw = new LambdaQueryWrapper<Schedule>();
        IPage page =new Page(current,size);
        scheduleMapper.selectPage(page,qw);
        return page;
    }

}
