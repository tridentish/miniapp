package com.itwu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itwu.entity.AwardRecord;
import com.itwu.entity.Prize;

public interface PrizeService extends IService<Prize> {

    IPage<Prize> getPage(int current, int size, Prize prize);

}
