package com.itwu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itwu.entity.Administrator;
import com.itwu.mapper.AdministratorMapper;
import com.itwu.service.AdministratorService;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator> implements AdministratorService {


}
