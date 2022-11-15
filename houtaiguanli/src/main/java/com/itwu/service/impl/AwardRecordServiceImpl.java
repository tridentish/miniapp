package com.itwu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itwu.entity.AwardRecord;
import com.itwu.mapper.AwardRecordMapper;
import com.itwu.service.AwardRecordService;
import org.springframework.stereotype.Service;

@Service
public class AwardRecordServiceImpl extends ServiceImpl<AwardRecordMapper, AwardRecord> implements AwardRecordService {
    @Override
    public IPage<AwardRecord> getPage(int current, int size, AwardRecord awardRecord) {
        return null;
    }
}
