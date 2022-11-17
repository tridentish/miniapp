package com.itwu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itwu.entity.SendRecord;
import com.itwu.entity.User;
import com.itwu.mapper.SendRecordMapper;
import com.itwu.service.SendRecordService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendRecordServiceImpl extends ServiceImpl<SendRecordMapper, SendRecord> implements SendRecordService {
    @Autowired
    SendRecordMapper sendRecordMapper;

    @Override
    public List<SendRecord> getRecord() {
        LambdaQueryWrapper<SendRecord> qw = new LambdaQueryWrapper<SendRecord>();
        //qw.orderByAsc();
        qw.orderByDesc(SendRecord::getId);
        return sendRecordMapper.selectList(qw);
    }
}
