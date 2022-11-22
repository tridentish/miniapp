package com.itwu.chenyidan.test.java.com.itheima.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTestCase {
    @Autowired
    private UserService userService;
    @Test
    void testGetById(){
        System.out.println(userService.getById(6));

    }

    @Test
    void testSave(){
        User user = new User();
        user.setStudent_name("测试数据123");
        user.setId(16);
        user.setIntroduction("测试数据123");
        userService.save(user);
        System.out.println(userService.getById(16));

    }

    @Test
    void testUpdate(){
        User user = new User();
        user.setStudent_name("测试数据123");
        user.setId(123);
        user.setIntroduction("测试数据123");
        userService.update(user);
    }

    @Test
    void testDelete(){
        userService.delete(9);
    }

    @Test
    void testGetAll(){
        userService.getAll();
    }

    @Test
    void testGetPage() {
        IPage<User> page = userService.getPage(6, 9);
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.getRecords());
    }
}
