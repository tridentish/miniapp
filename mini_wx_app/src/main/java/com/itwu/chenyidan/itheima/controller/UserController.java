
package com.itwu.chenyidan.itheima.controller;

import com.itheima.controller.utils.Result;
import com.itheima.domain.User;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping
    public Result getAll(){
        return new Result(true,userService.list());
    }
    @PostMapping
    public Result save(@RequestBody User user){
        return  new Result(userService.save(user));
    }
    @PutMapping
    public Result update(@RequestBody User user){
        return  new Result(userService.modify(user));
    }
    @DeleteMapping
    public Result delete(@PathVariable Integer id){
        return  new Result(userService.delete(id));
    }
    @GetMapping("id")
    public Result getById(@PathVariable Integer id)
    {
        return new Result(true,userService.getById(id));
    }
    @GetMapping("{currentPage}/{pageSize}")
    public Result getPage(@PathVariable int currentPage,@PathVariable int pageSize)
    {
        return new Result(true,userService.getPage(currentPage,pageSize));
    }

}

