package com.itwu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itwu.entity.R;
import com.itwu.entity.Schedule;
import com.itwu.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    //分页获取日程
    @GetMapping("{current}/{size}")
    public R getAll(@PathVariable Integer current,@PathVariable Integer size){
        IPage<Schedule> page = scheduleService.getPage(current,size);
        return new R(true,page);
    }

    //添加日程
    @PostMapping()
    public R saveSchedule(@RequestBody Schedule schedule){

        return new R(true,scheduleService.save(schedule),"添加成功");
    }
    //修改日程
    @PutMapping()
    public R updateSchedule(@RequestBody Schedule schedule){

        return new R(true,scheduleService.updateById(schedule),"修改成功");
    }

    //批量删除日程
    @DeleteMapping()
    public R deleteMany(@RequestBody List<Integer> ids){

        return new R(true,scheduleService.removeByIds(ids),"删除成功");
    }

}
