package com.itwu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itwu.entity.Prize;
import com.itwu.entity.User;
import com.itwu.mapper.PrizeMapper;
import com.itwu.service.PrizeService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrizeServiceImpl extends ServiceImpl<PrizeMapper, Prize> implements PrizeService {
    @Autowired
    private PrizeMapper prizeMapper;
    @Override
    public IPage<Prize> getPage(int current, int size, Prize prize) {
        LambdaQueryWrapper<Prize> qw = new LambdaQueryWrapper<Prize>();
        qw.like(Strings.isNotEmpty(prize.getLevel()),Prize::getLevel,prize.getLevel());
        qw.like(Strings.isNotEmpty(prize.getContent()),Prize::getContent,prize.getContent());
        IPage page =new Page(current,size);
        prizeMapper.selectPage(page,qw);
        return page;
    }
}
