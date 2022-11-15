package com.itwu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itwu.entity.Prize;
import com.itwu.entity.R;
import com.itwu.entity.User;
import com.itwu.service.AwardRecordService;
import com.itwu.service.PrizeService;
import com.itwu.service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("prize")
public class PrizeController {
    @Autowired
    private PrizeService prizeService;
    @Autowired
    private AwardRecordService awardRecordService;
    @Autowired
    private UserService userService;

    //分页获取奖品
    @GetMapping("{current}/{size}")
    public R getAll(@PathVariable Integer current, @PathVariable Integer size, Prize prize){
        IPage<Prize> page=prizeService.getPage(current,size,prize);
        return new R(true,page);
    }

    //添加奖品内容
    @PostMapping()
    public R savePrize(@RequestBody Prize prize){

        return new R(true,prizeService.save(prize),"添加成功");
    }
    //修改奖品内容
    @PutMapping()
    public R updateById(@RequestBody Prize prize){

        return new R(true,prizeService.updateById(prize),"修改成功");
    }

    //批量删除
    @DeleteMapping()
    public R deleteByIds(@RequestBody List<Integer> ids){

        return new R(true,prizeService.removeByIds(ids),"删除成功");
    }

    //分页按条件查看中奖名单
    @GetMapping("{id}/{current}/{size}")
    public R getRecord(@PathVariable Integer id, @PathVariable Integer current, @PathVariable Integer size, User user){
        user.setAward(id);
        IPage<User> page=userService.getAwardPage(current,size,user);
        return new R(true,page);
    }
}
