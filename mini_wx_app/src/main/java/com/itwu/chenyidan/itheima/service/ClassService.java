package com.itwu.chenyidan.itheima.service;

public interface ClassService {
    boolean save(Class class1);
    boolean update(Class class1);
    boolean delete(Integer id);
    Class getById(Integer id);
    Class getByProfession(String profession,String grade,String class_name);
}
