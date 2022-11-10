package com.itwu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itwu.entity.History;
import com.itwu.entity.User;
import com.itwu.mapper.HistoryMapper;
import com.itwu.service.HistoryService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History> implements HistoryService {

    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public IPage<History> getPage(int current, int size, History history) {
        LambdaQueryWrapper<History> qw = new LambdaQueryWrapper<History>();
        qw.like(Strings.isNotEmpty(history.getTitle()),History::getTitle,history.getTitle());
        //按时间排序
        qw.orderByDesc(History::getHistoryTime);
        IPage page =new Page(current,size);
        historyMapper.selectPage(page,qw);
        return page;
    }
}
