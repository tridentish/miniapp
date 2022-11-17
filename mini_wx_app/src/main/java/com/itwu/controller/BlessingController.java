package com.itwu.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.itwu.entity.Message;
import com.itwu.entity.R;
import com.itwu.entity.SendRecord;
import com.itwu.entity.User;
import com.itwu.mapper.MessageMapper;
import com.itwu.mapper.UserMapper;
import com.itwu.service.LoginService;
import com.itwu.service.MessageService;
import com.itwu.service.SendRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@RestController
@RequestMapping("/bless")
public class BlessingController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginService loginService;
    @Autowired
    private SendRecordService sendRecordService;

    //随机给前端五条寄语
    @GetMapping("/send")
    private R getBlessing(){
        int count=messageService.count();
        count=count -5;

        int num=(int)(Math.random()*count+1);
        List<Integer> ids = new List<Integer>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Integer> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Integer integer) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Integer> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Integer> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Integer get(int index) {
                return null;
            }

            @Override
            public Integer set(int index, Integer element) {
                return null;
            }

            @Override
            public void add(int index, Integer element) {

            }

            @Override
            public Integer remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Integer> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Integer> listIterator(int index) {
                return null;
            }

            @Override
            public List<Integer> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        ids.add(count);
        ids.add(count+1);
        ids.add(count+2);
        ids.add(count+3);
        ids.add(count+4);
        return new R(true,messageService.listByIds(ids));
    }

    //发送寄语记录按时间排序
    @GetMapping("/record")
    private R getRecord(){

        return new R(true,sendRecordService.getRecord());
    }

    //发送寄语
    @PostMapping()
    private R sendBlessing(@RequestHeader("Authorization") String token,@RequestBody String blessing){
        R<User> r = loginService.checkToken(token);
        User user =r.getData();
        user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId, user.getId()));

        SendRecord sendRecord =new SendRecord();
        sendRecord.setName(user.getStudentName());
        sendRecord.setContent(blessing);
        sendRecordService.save(sendRecord);
        //返回发送记录
        return new R(true,sendRecord,"发送成功");
    }


}
