package com.itwu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itwu.entity.Message;
import com.itwu.entity.R;
import com.itwu.entity.Schedule;
import com.itwu.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    //分页获取
    @GetMapping("{current}/{size}")
    public R getAll(@PathVariable Integer current, @PathVariable Integer size){
        IPage<Message> page = messageService.getPage(current,size);
        return new R(true,page);
    }

    //添加寄语
    @PostMapping()
    public R saveSchedule(@RequestBody Message message){

        return new R(true,messageService.save(message),"添加成功");
    }
    //修改寄语
    @PutMapping()
    public R updateSchedule(@RequestBody Message message){

        return new R(true,messageService.updateById(message),"修改成功");
    }

    //批量删除寄语
    @DeleteMapping()
    public R deleteMany(@RequestBody List<Integer> ids){

        return new R(true,messageService.removeByIds(ids),"删除成功");
    }
}
