//package com.itwu.chenyidan.itheima.service.impl;
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.itheima.dao.UserDao;
//import com.itheima.domain.User;
//import com.itheima.service.IUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//@Service
//public class UserServiceImpl extends ServiceImpl<UserDao,User> implements IUserService {
//
//    @Autowired
//    private UserDao userDao;
//    @Override
//    public boolean save(User user) {
//        return userDao.insert(user) > 0;
//    }
//
//    public boolean saveUser(User user){
//        return userDao.insert(user)>0;
//    }
//    public boolean modify(User user){
//        return userDao.updateById(user)>0;
//    }
//
//    @Override
//    public boolean update(User user) {
//        return userDao.updateById(user) > 0;
//    }
//
//    @Override
//    public boolean delete(Integer id) {
//        return userDao.deleteById(id) > 0;
//    }
//
//    @Override
//    public User getById(Integer id) {
//        return userDao.selectById(id);
//    }
//
//    @Override
//    public List<User> getAll() {
//        return userDao.selectList(null);
//    }
//
//    @Override
//    public IPage<User> getPage(int currentPage, int pageSize) {
//        IPage page = new Page(currentPage,pageSize);
//        userDao.selectPage(page, null);
//        return page;
//    }
//
//    @Override
//    public IPage<User> getPage(int currentPage, int pageSize, User user) {
//        IPage page = new Page(currentPage,pageSize);
//        userDao.selectPage(page,null);
//        return page;
//    }
//}
