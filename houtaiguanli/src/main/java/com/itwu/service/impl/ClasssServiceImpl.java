package com.itwu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itwu.entity.Classs;
import com.itwu.entity.User;
import com.itwu.mapper.ClasssMapper;
import com.itwu.service.ClassService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClasssServiceImpl extends ServiceImpl<ClasssMapper, Classs> implements ClassService {

    @Autowired
    private ClasssMapper classsMapper;
    @Override
    public IPage<Classs> getPage(int current, int size, Classs classs) {
        LambdaQueryWrapper<Classs> qw = new LambdaQueryWrapper<Classs>();
        qw.like(Strings.isNotEmpty(classs.getGrade()),Classs::getGrade,classs.getGrade());
        qw.like(Strings.isNotEmpty(classs.getProfession()),Classs::getProfession,classs.getProfession());
        qw.like(Strings.isNotEmpty(classs.getClassName()),Classs::getClassName,classs.getClassName());
        IPage page =new Page(current,size);
        classsMapper.selectPage(page,qw);
        return page;
    }
}
