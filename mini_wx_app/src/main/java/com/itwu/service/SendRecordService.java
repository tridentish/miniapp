package com.itwu.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itwu.entity.SendRecord;

import java.util.List;

public interface SendRecordService extends IService<SendRecord> {
    List<SendRecord> getRecord();
}
