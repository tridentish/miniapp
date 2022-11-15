package com.itwu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itwu.entity.Classs;
import com.itwu.entity.R;
import com.itwu.entity.User;
import com.itwu.service.ClassService;
import com.itwu.service.UserService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("class")
public class ClasssController {
    @Autowired
    private ClassService classService;
    @Autowired
    private UserService userService;

    //分页获取班级信息
    @GetMapping("{current}/{size}")
    public R getAll(@PathVariable Integer current, @PathVariable Integer size, Classs classs){
        IPage<Classs> page=classService.getPage(current,size,classs);
        return new R(true,page);
    }

    //添加班级
    @PostMapping
    public R saveClass(@RequestBody Classs classs){
        return new R(true,classService.save(classs),"添加成功");
    }
    //修改班级信息
    @PutMapping
    public R updateClass(@RequestBody Classs classs){
        return new R(true,classService.updateById(classs),"修改成功");
    }

    //删除班级
    @DeleteMapping("{id}")
    public R deleteClass(@PathVariable Integer id){
        return new R(true,classService.removeById(id),"删除成功");
    }

    //查看班级详细成员信息
    @GetMapping("{id}/{current}/{size}")
    public R getMember(@PathVariable Integer id,@PathVariable Integer current, @PathVariable Integer size,User user){
        user.setClassNum(id);
        IPage<User> page=userService.getClassPage(current,size,user);
        return new R(true,page);
    }

    //踢出班级
    @PutMapping("{cid}/{sid}")
    public R getOut(@PathVariable Integer cid,@PathVariable Integer sid){
        User user = userService.getById(sid);
        user.setClassNum(0);
        return new R(true,userService.updateById(user),"踢出成功");
    }


}
