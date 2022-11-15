package com.itwu.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itwu.entity.AwardRecord;
import com.itwu.entity.User;

public interface AwardRecordService extends IService<AwardRecord> {
    IPage<AwardRecord> getPage(int current, int size, AwardRecord awardRecord);
}
