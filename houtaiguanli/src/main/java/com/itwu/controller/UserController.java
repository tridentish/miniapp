package com.itwu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itwu.entity.R;
import com.itwu.entity.User;
import com.itwu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public R selectId(@PathVariable Integer id){
        //查看详细里显示的数据
        return new R(true,userService.getById(id));
    }

    @DeleteMapping("{id}")
    public R deleteById(@PathVariable Integer id){
        //删除用户信息
        return new R(userService.removeById(id));
    }

    @PutMapping()
    public R updateUser(@RequestBody User user){
        //修改用户信息
        return new R(userService.updateById(user));
    }

    @DeleteMapping
    public R deleteMany(@RequestBody List<Integer> ids){
        //按id数组批量删除用户数据
        return new R(userService.removeByIds(ids));
    }

    @GetMapping("{current}/{size}")
    public R selectPage(@PathVariable int current,@PathVariable int size,User user){//user属性在前端路径里"?studentNum=&studentName="
        //按条件分页查询
        IPage<User> page=userService.getPage(current,size,user);
        return new R(true,page);
    }

}
